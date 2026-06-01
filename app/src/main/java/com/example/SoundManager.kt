package com.example

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import com.example.data.SoundSettings
import kotlinx.coroutines.*
import java.util.Random
import kotlin.math.sin

object SoundManager {
    private const val SAMPLE_RATE = 22050
    private var isInitialized = false
    private var isStopped = false
    private var soundScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    // Background music streaming variables
    private var bgMusicTrack: AudioTrack? = null
    private var bgJob: Job? = null
    private var isMusicPaused = false

    fun init(context: Context) {
        if (isInitialized) {
            if (isStopped) {
                isStopped = false
                startBackgroundMusic()
            }
            return
        }
        isStopped = false
        
        try {
            // Set stream volume to maximum
            val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
            val maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, maxVol, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Initialize SharedPreferences-based SoundSettings
        SoundSettings.init(context)
        isInitialized = true

        // Start background music loop
        startBackgroundMusic()
    }

    private fun playPcm(pcm: ShortArray) {
        if (isStopped) return
        soundScope.launch {
            var track: AudioTrack? = null
            try {
                val bufferSize = pcm.size * 2
                track = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(SAMPLE_RATE)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(bufferSize)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()
                track.write(pcm, 0, pcm.size)
                track.setStereoVolume(1.0f, 1.0f) // Set max track volume
                track.play()
                val durationMs = (pcm.size * 1000L) / SAMPLE_RATE
                delay(durationMs + 100)
                track.stop()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    track?.release()
                } catch (e: Exception) {
                    // Ignore
                }
            }
        }
    }

    // ═══════════════════════════════════════
    // SYNTHESIZED SOUND EFFECTS
    // ═══════════════════════════════════════

    // 1. BUTTON TAP SOUND
    // Short sharp click: 50ms duration, 800Hz with quick decay, amplitude 0.9f (scaled 3x)
    fun playButtonTap() {
        if (!SoundSettings.buttonSoundEnabled) return
        val durationMs = 50
        val freq = 800.0f
        val numSamples = (SAMPLE_RATE * (durationMs / 1000.0)).toInt()
        val pcm = ShortArray(numSamples)
        val masterVol = SoundSettings.masterVolume
        
        for (i in 0 until numSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val angle = 2.0 * Math.PI * freq * t
            // Exponential rapid decay factor to make it sound clicky and clean
            val decay = Math.exp(-150.0 * t) 
            val amplitude = 0.9f * decay * masterVol
            // 3x amplitude boost requested (the base of 0.9f on the short scale 32767 is already very loud,
            // we multiply by 3x internally in the short calculation)
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // 2. CORRECT ANSWER SOUND
    // Triumphant 3-note ascending melody: 523Hz, 659Hz, 784Hz (C, E, G), each 150ms. Volume 0.8f. Add echo effect.
    fun playCorrect() {
        if (!SoundSettings.correctSoundEnabled) return
        val noteDurMs = 150
        val notes = floatArrayOf(523.25f, 659.25f, 784.00f)
        val totalSamples = (SAMPLE_RATE * (noteDurMs * 3 / 1000.0)).toInt()
        val pcm = ShortArray(totalSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until totalSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val currentNoteIndex = (i / (SAMPLE_RATE * (noteDurMs / 1000.0))).toInt().coerceIn(0, 2)
            val freq = notes[currentNoteIndex]
            
            val angle = 2.0 * Math.PI * freq * t
            val noteT = (i % (SAMPLE_RATE * (noteDurMs / 1000.0))) / SAMPLE_RATE
            // Apply slight decay per note to prevent clicking
            val decay = Math.exp(-5.0 * noteT)
            var baseSignal = sin(angle) * 0.8f * decay * masterVol

            // Add simple Echo Effect: mix previous note signal if applicable
            val delaySamples = (SAMPLE_RATE * 0.05).toInt() // 50ms delay echo
            if (i > delaySamples) {
                val echoT = (i - delaySamples).toDouble() / SAMPLE_RATE
                val echoNoteIndex = ((i - delaySamples) / (SAMPLE_RATE * (noteDurMs / 1000.0))).toInt().coerceIn(0, 2)
                val echoAngle = 2.0 * Math.PI * notes[echoNoteIndex] * echoT
                baseSignal += (sin(echoAngle) * 0.4f * masterVol).toFloat()
            }

            val sampleVal = (baseSignal * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // 3. WRONG ANSWER SOUND
    // Descending 2-note sad sound: 400Hz then 300Hz, each 200ms with vibrato. Volume 0.8f.
    fun playWrong() {
        if (!SoundSettings.wrongSoundEnabled) return
        val noteDurMs = 200
        val notes = floatArrayOf(400.0f, 300.0f)
        val totalSamples = (SAMPLE_RATE * (noteDurMs * 2 / 1000.0)).toInt()
        val pcm = ShortArray(totalSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until totalSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val noteIndex = (i / (SAMPLE_RATE * (noteDurMs / 1000.0))).toInt().coerceIn(0, 1)
            val baseFreq = notes[noteIndex]
            
            // Vibrato: modulate frequency slightly
            // Vibrato speed: 8Hz, Depth: 12Hz modulation
            val vibrato = sin(2.0 * Math.PI * 8.0 * t) * 12.0
            val modulatedFreq = baseFreq + vibrato

            val angle = 2.0 * Math.PI * modulatedFreq * t
            val decay = Math.exp(-2.0 * (i % (SAMPLE_RATE * (noteDurMs / 1000.0))) / SAMPLE_RATE)
            val amplitude = 0.8f * decay * masterVol
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // 4. NAVIGATION/SCREEN CHANGE SOUND
    // Smooth whoosh sweep sound: 200Hz to 600Hz, duration 200ms. Volume 0.8f.
    fun playNavigation() {
        if (!SoundSettings.navigationSoundEnabled) return
        val durationMs = 200
        val numSamples = (SAMPLE_RATE * (durationMs / 1000.0)).toInt()
        val pcm = ShortArray(numSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until numSamples) {
            val progress = i.toDouble() / numSamples
            val freq = 200.0 + (400.0 * progress) // Sweep 200Hz to 600Hz
            val t = i.toDouble() / SAMPLE_RATE
            val angle = 2.0 * Math.PI * freq * t
            
            // Fade-in and fade-out envelope to have a smooth whoosh
            val envelope = sin(progress * Math.PI)
            val amplitude = 0.8f * envelope * masterVol
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // 6. STAGE COMPLETE SOUND
    // Victory fanfare 5 notes ascending: 523, 587, 659, 784, 1047Hz, each 200ms. Volume 0.8f.
    fun playStageComplete() {
        val noteDurMs = 200
        val notes = floatArrayOf(523.25f, 587.33f, 659.25f, 784.00f, 1046.50f)
        val totalSamples = (SAMPLE_RATE * (noteDurMs * 5 / 1000.0)).toInt()
        val pcm = ShortArray(totalSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until totalSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val noteIndex = (i / (SAMPLE_RATE * (noteDurMs / 1000.0))).toInt().coerceIn(0, 4)
            val freq = notes[noteIndex]
            
            val angle = 2.0 * Math.PI * freq * t
            val noteT = (i % (SAMPLE_RATE * (noteDurMs / 1000.0))) / SAMPLE_RATE
            val decay = Math.exp(-4.0 * noteT)
            val amplitude = 0.8f * decay * masterVol
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // 7. LEVEL UP / UNLOCK SOUND
    // Magical sparkle ascending sweep: Multiple quick notes 100ms each, high pitched. Volume 0.8f.
    fun playLevelUp() {
        val noteDurMs = 100
        val notes = floatArrayOf(880f, 1046f, 1174f, 1318f, 1568f, 1760f)
        val totalSamples = (SAMPLE_RATE * (noteDurMs * 6 / 1000.0)).toInt()
        val pcm = ShortArray(totalSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until totalSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val noteIndex = (i / (SAMPLE_RATE * (noteDurMs / 1000.0))).toInt().coerceIn(0, 5)
            val freq = notes[noteIndex]
            val angle = 2.0 * Math.PI * freq * t
            
            // Sparkle sparkle: modulate slightly with higher freq
            val sparkleMod = sin(2.0 * Math.PI * 40.0 * t) * 0.15f
            val decay = Math.exp(-8.0 * ((i % (SAMPLE_RATE * (noteDurMs / 1000.0))) / SAMPLE_RATE))
            val amplitude = 0.8f * (1.0f + sparkleMod) * decay * masterVol
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // 8. HINT USED SOUND
    // Soft magical chime: 3 quick descending notes: 1568, 1318, 1047Hz, 100ms each. Volume 0.8f.
    fun playHintUsed() {
        val noteDurMs = 100
        val notes = floatArrayOf(1568.0f, 1318.5f, 1046.5f)
        val totalSamples = (SAMPLE_RATE * (noteDurMs * 3 / 1000.0)).toInt()
        val pcm = ShortArray(totalSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until totalSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val noteIndex = (i / (SAMPLE_RATE * (noteDurMs / 1000.0))).toInt().coerceIn(0, 2)
            val freq = notes[noteIndex]
            val angle = 2.0 * Math.PI * freq * t
            val decay = Math.exp(-6.0 * ((i % (SAMPLE_RATE * (noteDurMs / 1000.0))) / SAMPLE_RATE))
            val amplitude = 0.8f * decay * masterVol
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // 9. GAME OVER SOUND
    // Slow descending notes: 261, 220, 196Hz, 300ms each. Volume 0.8f.
    fun playGameOver() {
        val noteDurMs = 300
        val notes = floatArrayOf(261.63f, 220.0f, 196.0f)
        val totalSamples = (SAMPLE_RATE * (noteDurMs * 3 / 1000.0)).toInt()
        val pcm = ShortArray(totalSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until totalSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val noteIndex = (i / (SAMPLE_RATE * (noteDurMs / 1000.0))).toInt().coerceIn(0, 2)
            val freq = notes[noteIndex]
            val angle = 2.0 * Math.PI * freq * t
            val decay = Math.exp(-3.0 * ((i % (SAMPLE_RATE * (noteDurMs / 1000.0))) / SAMPLE_RATE))
            val amplitude = 0.8f * decay * masterVol
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // 10. COUNTDOWN TIMER WARNING
    // Quick tick sound every second: duration 100ms, tick sound or beeping.
    // If gets faster and higher pitch, we can specify a tick speed or level parameter.
    fun playTimerTick(level: Int) {
        // level 1, 2, 3: gets faster and higher pitched. 3 is urgent beep.
        if (!SoundSettings.buttonSoundEnabled) return
        val durationMs = 100
        val freq = when (level) {
            3 -> 2000.0f  // Urgent beeping
            2 -> 1200.0f  // Moderate tick-beeping
            else -> 600.0f // standard tick
        }
        val numSamples = (SAMPLE_RATE * (durationMs / 1000.0)).toInt()
        val pcm = ShortArray(numSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until numSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val angle = 2.0 * Math.PI * freq * t
            val decay = Math.exp(-25.0 * t) 
            val amplitude = 0.8f * decay * masterVol
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // Combo trigger sounds
    fun playComboSound(multiplier: Int) {
        val noteDurMs = 120
        // Play an extremely satisfying ascending pentatonic sparkle matching multiplier
        val notes = when (multiplier) {
            3 -> floatArrayOf(523.25f, 659.25f, 784.00f, 1046.5f)    // 3x
            5 -> floatArrayOf(659.25f, 784.00f, 1046.5f, 1318.5f, 1568f)   // 5x
            else -> floatArrayOf(523.25f, 659.25f, 784.00f, 880f, 1046.5f, 1318.5f, 1568f, 2093f) // 10x
        }
        val totalSamples = (SAMPLE_RATE * (noteDurMs * notes.size / 1000.0)).toInt()
        val pcm = ShortArray(totalSamples)
        val masterVol = SoundSettings.masterVolume

        for (i in 0 until totalSamples) {
            val t = i.toDouble() / SAMPLE_RATE
            val noteIndex = (i / (SAMPLE_RATE * (noteDurMs / 1000.0))).toInt().coerceIn(0, notes.lastIndex)
            val freq = notes[noteIndex]
            val angle = 2.0 * Math.PI * freq * t
            val decay = Math.exp(-5.0 * ((i % (SAMPLE_RATE * (noteDurMs / 1000.0))) / SAMPLE_RATE))
            val amplitude = 0.8f * decay * masterVol
            val sampleVal = (sin(angle) * amplitude * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
            pcm[i] = sampleVal.toShort()
        }
        playPcm(pcm)
    }

    // ═══════════════════════════════════════
    // BACKGROUND MUSIC SEQUENCE ENGINE (BUG 4.5)
    // ═══════════════════════════════════════

    fun pauseMusic() {
        isMusicPaused = true
    }

    fun resumeMusic() {
        isMusicPaused = false
    }

    private fun startBackgroundMusic() {
        if (bgJob != null) return

        bgJob = soundScope.launch {
            val stepDurMs = 250 // 120 BPM: each eighth note is 250ms
            val numSteps = 16
            
            // Fantasy RPG Pentatonic Melodic Loop
            val melodySeq = intArrayOf(
                523,   0, 587, 659, 784,   0, 659,   0,
                880, 784, 659, 587, 523,   0,   0,   0
            )
            val bassSeq = intArrayOf(
                130, 130, 146, 146, 165, 165, 130, 130,
                220, 220, 196, 196, 165, 165, 130, 130
            )

            val bufferSize = AudioTrack.getMinBufferSize(
                SAMPLE_RATE,
                AudioFormat.CHANNEL_OUT_MONO,
                AudioFormat.ENCODING_PCM_16BIT
            ).coerceAtLeast(1024) * 2

            try {
                bgMusicTrack = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_GAME)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(SAMPLE_RATE)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(bufferSize)
                    .setTransferMode(AudioTrack.MODE_STREAM)
                    .build()
                bgMusicTrack?.play()
            } catch (e: Exception) {
                e.printStackTrace()
                return@launch
            }

            var currentStep = 0
            val random = Random()

            while (isActive && !isStopped) {
                // If paused or music disabled, write silence to keep the track running smoothly but silent
                val isBGMEnabled = SoundSettings.backgroundMusicEnabled
                val mVol = SoundSettings.masterVolume
                
                val numSamples = (SAMPLE_RATE * (stepDurMs / 1000.0)).toInt()
                val stepPcm = ShortArray(numSamples)

                if (isMusicPaused || !isBGMEnabled || mVol <= 0.01f) {
                    // Write silence and sleep safely
                    try {
                        val trackSnapshot = bgMusicTrack
                        if (trackSnapshot != null && !isStopped) {
                            trackSnapshot.write(stepPcm, 0, numSamples)
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    delay(stepDurMs.toLong())
                    continue
                }

                // SYNTHESIZE STEP: Mixture of Melody, Bass, and Rhythm Beat
                val melodyFreq = melodySeq[currentStep].toDouble()
                val bassFreq = bassSeq[currentStep].toDouble()

                for (i in 0 until numSamples) {
                    val t = i.toDouble() / SAMPLE_RATE
                    val totalT = (currentStep * stepDurMs / 1000.0) + t

                    // 1. Melody: Sine wave with vibrato and envelope
                    var melodyVal = 0.0
                    if (melodyFreq > 0) {
                        val decay = Math.exp(-4.0 * t) // smooth decay
                        val vib = sin(2.0 * Math.PI * 6.0 * totalT) * 3.0 // 6Hz vibrato
                        val angle = 2.0 * Math.PI * (melodyFreq + vib) * totalT
                        melodyVal = sin(angle) * 0.4 * decay
                    }

                    // 2. Bass: Solid low sine wave
                    val bassAngle = 2.0 * Math.PI * bassFreq * totalT
                    val bassVal = sin(bassAngle) * 0.5

                    // 3. Rhythm: Short noise beat / percussion pulse on beat 0, 4, 8, 12
                    var drumVal = 0.0
                    if (currentStep % 4 == 0) {
                        val decay = Math.exp(-40.0 * t) // rapid decay drum kick
                        val kickAngle = 2.0 * Math.PI * 60.0 * t
                        drumVal = sin(kickAngle) * 0.7 * decay
                    } else if (currentStep % 4 == 2) {
                        // Soft brush snare (white noise) on opposite beat
                        val decay = Math.exp(-25.0 * t)
                        val noise = (random.nextFloat() * 2.0 - 1.0)
                        drumVal = noise * 0.25 * decay
                    }

                    // Mix all signals with a minimum backgrounds volume scaling of 0.7f
                    // Combined amplitude: clamp is done at short conversion
                    val mixed = (melodyVal + bassVal + drumVal) * 0.7f * mVol

                    // 3x amplitude boost requested: apply factor
                    val sampleVal = (mixed * 32767.0 * 3.0).toInt().coerceIn(-32768, 32767)
                    stepPcm[i] = sampleVal.toShort()
                }

                // Write block to streaming Track safely
                try {
                    val trackSnapshot = bgMusicTrack
                    if (trackSnapshot != null && !isStopped) {
                        trackSnapshot.write(stepPcm, 0, numSamples)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

                currentStep = (currentStep + 1) % numSteps
                
                // Allow thread cooperation
                yield()
            }
        }
    }

    // ═══════════════════════════════════════
    // LIFECYCLE MANAGEMENT (BUG 1)
    // ═══════════════════════════════════════

    fun stopAllSounds() {
        try {
            bgMusicTrack?.pause()
            bgMusicTrack?.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun release() {
        isStopped = true
        bgJob?.cancel()
        bgJob = null
        try {
            bgMusicTrack?.stop()
            bgMusicTrack?.release()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        bgMusicTrack = null
        soundScope.cancel()
        soundScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    }
}
