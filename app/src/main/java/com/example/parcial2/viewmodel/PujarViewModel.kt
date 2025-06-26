package com.example.parcial2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial2.data.Repository.RifaRepository
import com.example.parcial2.data.Modelo.Rifa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PujarViewModel(private val repository: RifaRepository) : ViewModel() {
    private val _estado = MutableStateFlow<String?>(null)
    val estado: StateFlow<String?> = _estado

    fun crearRifa(nombre: String, fecha: String) {
        viewModelScope.launch {
            if (nombre.isBlank() || fecha.isBlank()) {
                _estado.value = "Debe completar todos los campos"
                return@launch
            }
            repository.crear(Rifa(nombre = nombre, fecha = fecha))
            _estado.value = "Rifa creada correctamente"
        }
    }
}