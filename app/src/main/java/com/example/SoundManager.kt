package com.example

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.os.Build

object SoundManager {
    private var isMuted = false
    private var musicTrack: AudioTrack? = null
    private var isMusicRunning = false
    private var musicThread: Thread? = null

    fun toggleMute(): Boolean {
        isMuted = !isMuted
        if (isMuted) {
            stopBackgroundMusic()
        } else {
            startBackgroundMusic()
        }
        return isMuted
    }

    fun isMuted() = isMuted

    fun playClick() {
        if (isMuted) return
        playSineWave(650f, 0.04f, 0.25f) // crisp click
    }

    fun playWhoosh() {
        if (isMuted) return
        playSlidingTone(180f, 850f, 0.15f, 0.2f) // smooth whoosh
    }

    fun playCorrect() {
        if (isMuted) return
        // upbeat medieval fanfare chord: CEG
        playChords(listOf(listOf(523.25f, 659.25f, 783.99f)), 0.25f, 0.3f)
    }

    fun playWrong() {
        if (isMuted) return
        // low flat sad interval
        playChords(listOf(listOf(220.0f, 233.08f)), 0.35f, 0.3f)
    }

    fun playVictoryFanfare() {
        if (isMuted) return
        // full triumphant arpeggio and chords
        Thread {
            try {
                // play a sequence of notes
                val notes = listOf(523.25f, 659.25f, 783.99f, 1046.50f)
                for (note in notes) {
                    playSineWave(note, 0.12f, 0.2f)
                    Thread.sleep(70)
                }
                // then final big chord
                playChords(listOf(listOf(523.25f, 659.25f, 783.99f, 1046.50f)), 0.5f, 0.25f)
            } catch (e: Exception) {}
        }.start()
    }

    fun startBackgroundMusic() {
        if (isMuted || isMusicRunning) return
        isMusicRunning = true
        musicThread = Thread {
            try {
                val sampleRate = 22050 // lower sample rate for size and performance
                val minSize = AudioTrack.getMinBufferSize(
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT
                )
                val track = AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    minSize.coerceAtLeast(sampleRate * 2),
                    AudioTrack.MODE_STREAM
                )
                musicTrack = track
                track.play()

                // Soft fantasy melody (pentatonic A-minor)
                val melody = listOf(220.0f, 246.94f, 261.63f, 293.66f, 329.63f, 392.00f, 329.63f, 293.66f)
                var noteIdx = 0

                while (isMusicRunning && !isMuted) {
                    val freq = melody[noteIdx]
                    noteIdx = (noteIdx + 1) % melody.size

                    // Generate half a second (500ms) of a soft, vibrating sine wave string sound
                    val duration = 0.6f
                    val numSamples = (duration * sampleRate).toInt()
                    val buffer = ShortArray(numSamples)

                    for (i in 0 until numSamples) {
                        if (!isMusicRunning || isMuted) break
                        val progress = i.toDouble() / numSamples
                        val angle = 2.0 * Math.PI * freq * i / sampleRate
                        
                        // Vibrato
                        val vibrato = 1.0 + 0.012 * Math.sin(2.0 * Math.PI * 6.0 * i / sampleRate)
                        // Soft envelope fade-in & fade-out
                        val envelope = when {
                            progress < 0.15 -> progress / 0.15
                            progress > 0.8 -> (1.0 - progress) / 0.2
                            else -> 1.0
                        }

                        buffer[i] = (Math.sin(angle * vibrato) * 1100.0 * envelope).toInt().toShort()
                    }
                    if (isMusicRunning && !isMuted) {
                        track.write(buffer, 0, numSamples)
                    }
                    // Wait a moment between melody steps to prevent stuttering
                    Thread.sleep(500)
                }
            } catch (e: Exception) {
                // Ignore
            } finally {
                try {
                    musicTrack?.stop()
                    musicTrack?.release()
                } catch (e: Exception) {}
                musicTrack = null
            }
        }
        musicThread?.start()
    }

    fun stopBackgroundMusic() {
        isMusicRunning = false
        musicTrack?.flush()
        musicThread?.interrupt()
        musicThread = null
    }

    private fun playSineWave(freq: Float, durationSeconds: Float, volume: Float) {
        Thread {
            try {
                val sampleRate = 44100
                val numSamples = (durationSeconds * sampleRate).toInt()
                val samples = ShortArray(numSamples)
                for (i in 0 until numSamples) {
                    val progress = i.toDouble() / numSamples
                    val angle = 2.0 * Math.PI * freq * i / sampleRate
                    val envelope = if (progress > 0.8) (1.0 - progress) / 0.2 else 1.0
                    samples[i] = (Math.sin(angle) * 32767.0 * volume * envelope).toInt().toShort()
                }
                val track = AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    numSamples * 2,
                    AudioTrack.MODE_STATIC
                )
                track.write(samples, 0, numSamples)
                track.play()
                Thread.sleep((durationSeconds * 1000).toLong() + 30)
                track.release()
            } catch (e: Exception) {}
        }.start()
    }

    private fun playSlidingTone(startFreq: Float, endFreq: Float, durationSeconds: Float, volume: Float) {
        Thread {
            try {
                val sampleRate = 44100
                val numSamples = (durationSeconds * sampleRate).toInt()
                val samples = ShortArray(numSamples)
                for (i in 0 until numSamples) {
                    val progress = i.toDouble() / numSamples
                    val freq = startFreq + (endFreq - startFreq) * progress
                    val angle = 2.0 * Math.PI * freq * i / sampleRate
                    val envelope = if (progress > 0.8) (1.0 - progress) / 0.2 else 1.0
                    samples[i] = (Math.sin(angle) * 32767.0 * volume * envelope).toInt().toShort()
                }
                val track = AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    numSamples * 2,
                    AudioTrack.MODE_STATIC
                )
                track.write(samples, 0, numSamples)
                track.play()
                Thread.sleep((durationSeconds * 1000).toLong() + 30)
                track.release()
            } catch (e: Exception) {}
        }.start()
    }

    private fun playChords(chords: List<List<Float>>, durationSeconds: Float, volume: Float) {
        Thread {
            try {
                val sampleRate = 44100
                val numSamples = (durationSeconds * sampleRate).toInt()
                val samples = ShortArray(numSamples)

                for (chord in chords) {
                    for (i in 0 until numSamples) {
                        val progress = i.toDouble() / numSamples
                        var value = 0.0
                        for (freq in chord) {
                            val angle = 2.0 * Math.PI * freq * i / sampleRate
                            value += Math.sin(angle)
                        }
                        // average the frequencies to avoid clipping
                        value /= chord.size
                        val envelope = if (progress > 0.8) (1.0 - progress) / 0.2 else 1.0
                        samples[i] = (value * 32767.0 * volume * envelope).toInt().toShort()
                    }

                    val track = AudioTrack(
                        AudioManager.STREAM_MUSIC,
                        sampleRate,
                        AudioFormat.CHANNEL_OUT_MONO,
                        AudioFormat.ENCODING_PCM_16BIT,
                        numSamples * 2,
                        AudioTrack.MODE_STATIC
                    )
                    track.write(samples, 0, numSamples)
                    track.play()
                    Thread.sleep((durationSeconds * 1000).toLong() + 30)
                    track.release()
                }
            } catch (e: Exception) {}
        }.start()
    }
}
