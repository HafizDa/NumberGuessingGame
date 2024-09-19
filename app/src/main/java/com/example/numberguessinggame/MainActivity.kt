package com.example.numberguessinggame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessingGameUI()
        }
    }
}

@Composable
fun GuessingGameUI() {
    var userGuess by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("Guess a number between 1 and 10") }
    var game = remember { NumberGame(1..10) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Guess a number between 1 and 10",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Input field for guessing
        TextField(
            value = userGuess,
            onValueChange = { userGuess = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),


        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button to submit the guess, with fixed width of 80dp
        Button(
            onClick = {
                val guess = userGuess.toIntOrNull()
                if (guess != null) {
                    when (val result = game.makeGuess(guess)) {
                        GuessResult.HIGH -> resultMessage = "HIGH [1..10]."
                        GuessResult.LOW -> resultMessage = "LOW [1..10]."
                        GuessResult.HIT -> resultMessage = "You got it! The number was $guess."
                    }
                } else {
                    resultMessage = "Please enter a valid number."
                }
            },
            modifier = Modifier.width(150.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A5678))

        ) {
            Text("Make Guess!")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = resultMessage,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGuessingGameUI() {
    GuessingGameUI()
}
