package com.pratik.uicomponentcreation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.TextUtils
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import com.pratik.uicomponentcreation.databinding.ActivityVoiceRecognitionBinding
import java.util.*


class VoiceRecognitionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoiceRecognitionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoiceRecognitionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            recognitionCustom.launch(null)
        }

    }


    private val recognitionCustom = registerForActivityResult(RecognitionContract()) { result ->
        binding.tvInput.editText?.setText(result)
    }

}


class RecognitionContract : ActivityResultContract<Int, String>() {
    override fun createIntent(context: Context, input: Int?): Intent {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US")
        return intent
    }


    override fun parseResult(resultCode: Int, intent: Intent?): String {
        val spokenText = intent?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)?.get(0) ?: ""
        return spokenText
    }
}