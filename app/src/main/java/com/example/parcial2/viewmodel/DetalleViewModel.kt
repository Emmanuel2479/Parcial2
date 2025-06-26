package com.example.parcial2.viewmodel

import androidx.lifecycle.ViewModel
import com.example.parcial2.data.Repository.RifaRepository
import com.example.parcial2.data.Modelo.Rifa

class DetalleViewModel(private val repository: RifaRepository) : ViewModel() {
    suspend fun obtenerRifaPorId(id: Int): Rifa? = repository.obtenerPorId(id)
    suspend fun eliminarRifa(id: Int) = repository.eliminar(id)
}