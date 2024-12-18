package com.example.colorindication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.colorindication.ui.theme.ColorIndicationTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorIndicationTheme {
                LoadingScreen()
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    var showLoading by remember { mutableStateOf(false) }
    var showProgram by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (showLoading) {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        } else if (showProgram) {
            ProgramScreen()
        } else {
            Button(onClick = {
                showLoading = true
                scope.launch {
                    delay(3000)
                    showLoading = false
                    showProgram = true
                }
            }) {
                Text("Начать загрузку")
            }
        }
    }
}

@Composable
fun ProgramScreen() {
    var counter by remember { mutableIntStateOf(0) }
    var color by remember { mutableStateOf(Color.Unspecified) }
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = {
                counter++
                color = Color(
                    Random.nextInt(0, 256) / 255f,
                    Random.nextInt(0, 256) / 255f,
                    Random.nextInt(0, 256) / 255f,
                    1f
                )
            },
            colors = ButtonDefaults.buttonColors(color),
        ) {
            Text(
                text = counter.toString(),
            )
        }
    }
}