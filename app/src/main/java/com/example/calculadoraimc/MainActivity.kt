package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import com.example.calculadoraimc.viewmodel.IMCViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun Home() {

    var weightTextValue by remember { mutableStateOf("") }
    var heightTextValue by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray),
                title = {
                    Text(
                        text = "Calculadora IMC",
                        color = Color.White
                    )
                }
            )
        },
        modifier = Modifier
            .fillMaxSize()

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(LightGray)
        ) {
            val viewModel by remember { mutableStateOf(IMCViewModel()) }
            val imcValue by viewModel.imc.collectAsState()
            val levelImcValue by viewModel.levelImc.collectAsState()

            TextField(
                value = weightTextValue,
                onValueChange = { newText ->
                    weightTextValue = newText.replace(",", ".")
                },
                label = {
                    Text("Digite seu peso (kg):")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.DarkGray,
                    unfocusedTextColor = Gray,
                    focusedContainerColor = LightGray,
                    unfocusedContainerColor = LightGray,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.DarkGray,
                    unfocusedIndicatorColor = Gray,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                )
            )

            TextField(
                value = heightTextValue,
                onValueChange = { newText ->
                    heightTextValue = newText.replace(",", ".")
                },
                label = { Text("Digite sua altura (m):") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.DarkGray,
                    unfocusedTextColor = Gray,
                    focusedContainerColor = LightGray,
                    unfocusedContainerColor = LightGray,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.DarkGray,
                    unfocusedIndicatorColor = Gray,
                    unfocusedLabelColor = Color.Black,
                    focusedLabelColor = Color.Black,
                )
            )

            Button(
                onClick = {
                    viewModel.calculateIMC(weightTextValue.toFloat(), heightTextValue.toFloat())
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 128.dp, end = 128.dp),
                colors = ButtonColors(
                    contentColor = Color.White,
                    containerColor = Color.DarkGray,
                    disabledContainerColor = LightGray,
                    disabledContentColor = Gray
                )
            ) {
                Text("Calcular")
            }

            Spacer(
                modifier = Modifier.padding(32.dp)
            )

            Text(
                text = "IMC: $imcValue",
                modifier = Modifier.padding(start = 32.dp)
            )

            Text(
                text = "Classificação: $levelImcValue",
                modifier = Modifier.padding(start = 32.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    CalculadoraIMCTheme {
        Home()
    }
}