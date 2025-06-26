package com.example.parcial2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial2.data.Modelo.Rifa
import com.example.parcial2.data.Repository.RifaRepository
import kotlinx.coroutines.launch

class DetalleViewModel(private val repository: RifaRepository) : ViewModel() {
    suspend fun obtenerRifaPorId(id: String): Rifa? {
        return try {
            repository.obtenerPorId(id)
        } catch (e: Exception) {
            null
        }
    }

    suspend fun eliminarRifa(id: String) {
        try {
            repository.eliminar(id)
        } catch (_: Exception) { }
    }

    fun guardarCambios(id: String, rifaActualizada: Rifa) {
        viewModelScope.launch {
            try {
                repository.actualizar(id, rifaActualizada)
            } catch (_: Exception) { }
        }
    }
}