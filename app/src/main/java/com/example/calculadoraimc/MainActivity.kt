package com.example.calculadoraimc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme
import kotlin.math.pow
import kotlin.math.sqrt

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
fun Home(modifier: Modifier = Modifier) {

    var peso: Float = 77F
    var altura: Float = 1.8F

    var imc: Float = imcCalc(peso, altura)


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

        Text(
            text = "Hello world! \n\n$imc",
            modifier = Modifier.padding(innerPadding)
        )
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