package com.papb.tugasm2papb

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.papb.tugasm2papb.ui.theme.TugasPAPBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasPAPBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyScreen() {
    var outputText by remember { mutableStateOf("") }
    var inputText by remember { mutableStateOf("") }
    var inputNum by remember { mutableStateOf("") }

    val isFormFilled = remember(inputText, inputNum) {
        inputText.isNotBlank() && inputNum.isNotBlank()
    }

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = outputText)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Masukkan Nama") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.5f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = inputNum,
            onValueChange = {inputNum = it},
            label = { Text("Masukkan NIM") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.5f),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (isFormFilled) {
                    outputText = "${inputText}\n\n${inputNum}"
                }
            },
            enabled = isFormFilled,
            colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            modifier = Modifier.combinedClickable(
                onClick = {},
                onLongClick = {
                    Toast.makeText(context, "$inputText - $inputNum", Toast.LENGTH_SHORT).show()
                }
            )
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TugasPAPBTheme {
        MyScreen()
    }
}
