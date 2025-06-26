package com.example.parcial2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial2.data.Modelo.Rifa
import com.example.parcial2.data.Repository.RifaRepository
import kotlinx.coroutines.launch

class PujarViewModel(private val repository: RifaRepository) : ViewModel() {
    fun crearRifa(nombre: String, fecha: String) {
        viewModelScope.launch {
            val nueva = Rifa(
                nombre = nombre,
                fecha = fecha,
                boletoGanador = "",
                numerosSeleccionados = emptyList(),
                habilitada = true
            )
            repository.crear(nueva)
        }
    }
}