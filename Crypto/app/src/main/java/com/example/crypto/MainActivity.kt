package com.example.crypto

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.crypto.ui.theme.CryptoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val message = HashTrials.hashMessage("Hello world".toByteArray())
                    Log.d(
                        HashTrials.TAG,
                        "onCreate: ${message.joinToString("") { "%02x".format(it) }}"
                    )

                    val keyAliases = DigiSignTrials.listAllKeys()
                    while (keyAliases.hasMoreElements()) {
                        Log.d(DigiSignTrials.TAG, "onCreate: ${keyAliases.nextElement()}")
                    }
                }
            }
        }
    }
}