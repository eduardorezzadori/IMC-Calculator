package com.example.calculadoraimc.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class IMCViewModel : ViewModel() {
    private val _imc = MutableStateFlow<Float>(0F)
    val imc = _imc.asStateFlow()

    private val _levelImc = MutableStateFlow<String>("")
    val levelImc = _levelImc.asStateFlow()

    fun calculateIMC(weight: Float, height: Float) {
        val imcValue = weight / (height * height)
        _imc.value = imcValue
        _levelImc.value = getLevelImc(imcValue)
    }

    private fun getLevelImc(imcValue: Float): String {
        return when {
            imcValue < 18.5 -> "Abaixo do peso"
            imcValue < 25 -> "Peso normal"
            imcValue < 30 -> "Sobrepeso"
            imcValue < 35 -> "Obesidade"
            else -> "Obesidade grave"
        }
    }
}