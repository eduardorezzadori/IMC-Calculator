package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import kotlin.math.pow

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

    var textValuePeso by remember { mutableStateOf("") }
    var textValueAltura by remember { mutableStateOf("") }
    var imcResult by remember { mutableStateOf("") }
    var imc = 0F

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Cyan),
                title = {
                    Text(
                        text = "Calculadora IMC",
                        color = Color.Gray
                    )
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier.padding(innerPadding)
        ) {

            TextField(
                value = textValuePeso,
                onValueChange = { newText ->
                    textValuePeso = newText
                },
                label = { Text("Digite seu peso:") }
            )
            TextField(
                value = textValueAltura,
                onValueChange = { newText ->
                    textValueAltura = newText
                },
                label = { Text("Digite sua altura:") }
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Button(
                onClick = {
                    imc = imcCalc(textValuePeso.toFloat(), textValueAltura.toFloat())
                    imcResult = imc.toString()
                },
            ) {
                Text("Calcular")
            }

            Text(
                text = "IMC: $imcResult",
            )
        }
    }
}

fun imcCalc(p: Float, a: Float): Float {
    val imc: Float = p / (a.pow(2))
    return imc
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    CalculadoraIMCTheme {
        Home()
    }
}