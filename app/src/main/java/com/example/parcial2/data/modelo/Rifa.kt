package com.example.parcial2.data.Modelo

data class Rifa(
    val id: String = "", // MockAPI usa String como ID
    val nombre: String,
    val fecha: String,
    val boletoGanador: String = "",
    val numerosSeleccionados: List<Int> = emptyList(),
    val habilitada: Boolean = true
)