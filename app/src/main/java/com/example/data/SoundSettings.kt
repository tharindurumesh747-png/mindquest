package com.example.data

import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object SoundSettings {
    private const val PREFS_NAME = "mindquest_sound_settings"
    private const val KEY_MASTER_VOLUME = "master_volume"
    private const val KEY_BG_MUSIC = "bg_music_enabled"
    private const val KEY_BUTTON_SOUND = "button_sound_enabled"
    private const val KEY_CORRECT_SOUND = "correct_sound_enabled"
    private const val KEY_WRONG_SOUND = "wrong_sound_enabled"
    private const val KEY_NAV_SOUND = "nav_sound_enabled"

    var masterVolume = 1.0f
        set(value) {
            field = value.coerceIn(0.0f, 1.0f)
            _masterVolumeFlow.value = field
        }

    var backgroundMusicEnabled = true
        set(value) {
            field = value
            _backgroundMusicFlow.value = field
        }

    var buttonSoundEnabled = true
    var correctSoundEnabled = true
    var wrongSoundEnabled = true
    var navigationSoundEnabled = true

    private val _masterVolumeFlow = MutableStateFlow(1.0f)
    val masterVolumeFlow: StateFlow<Float> = _masterVolumeFlow

    private val _backgroundMusicFlow = MutableStateFlow(true)
    val backgroundMusicFlow: StateFlow<Boolean> = _backgroundMusicFlow

    private fun getAttributionContext(context: Context): Context {
        return if (android.os.Build.VERSION.SDK_INT >= 30) {
            context.createAttributionContext("default")
        } else {
            context
        }
    }

    fun init(context: Context) {
        val attrContext = getAttributionContext(context)
        val prefs = attrContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        masterVolume = prefs.getFloat(KEY_MASTER_VOLUME, 1.0f)
        backgroundMusicEnabled = prefs.getBoolean(KEY_BG_MUSIC, true)
        buttonSoundEnabled = prefs.getBoolean(KEY_BUTTON_SOUND, true)
        correctSoundEnabled = prefs.getBoolean(KEY_CORRECT_SOUND, true)
        wrongSoundEnabled = prefs.getBoolean(KEY_WRONG_SOUND, true)
        navigationSoundEnabled = prefs.getBoolean(KEY_NAV_SOUND, true)
    }

    fun save(context: Context) {
        val attrContext = getAttributionContext(context)
        val prefs = attrContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().apply {
            putFloat(KEY_MASTER_VOLUME, masterVolume)
            putBoolean(KEY_BG_MUSIC, backgroundMusicEnabled)
            putBoolean(KEY_BUTTON_SOUND, buttonSoundEnabled)
            putBoolean(KEY_CORRECT_SOUND, correctSoundEnabled)
            putBoolean(KEY_WRONG_SOUND, wrongSoundEnabled)
            putBoolean(KEY_NAV_SOUND, navigationSoundEnabled)
            apply()
        }
    }

    fun loadDefaults(context: Context) {
        masterVolume = 1.0f
        backgroundMusicEnabled = true
        buttonSoundEnabled = true
        correctSoundEnabled = true
        wrongSoundEnabled = true
        navigationSoundEnabled = true
        save(context)
    }
}
