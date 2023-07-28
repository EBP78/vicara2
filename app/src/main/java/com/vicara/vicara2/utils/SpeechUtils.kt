package com.vicara.vicara2.utils

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class SpeechUtils(private val context: Context) : TextToSpeech.OnInitListener {

    private var tts : TextToSpeech? = TextToSpeech(context, this)
    var enabled = true

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            // buat ini bisa ganti bahasa = mungkin bisa buat pake preference
            val result = tts!!.setLanguage(Locale("id", "ID"))

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS","The Language not supported!")
            } else {
                Log.e("TTS","The Language not supported!")
                enabled = true
            }
        }
    }

    fun speakOut(text: String) {
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    fun finish(){
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
    }
}