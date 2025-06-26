package com.example.parcial2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.parcial2.data.Modelo.Rifa
import com.example.parcial2.data.Repository.RifaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListaViewModel(private val repository: RifaRepository) : ViewModel() {
    private val _rifas = MutableStateFlow<List<Rifa>>(emptyList())
    val rifas: StateFlow<List<Rifa>> = _rifas

    init {
        cargarRifas()
    }

    fun cargarRifas() {
        viewModelScope.launch {
            try {
                _rifas.value = repository.obtenerTodas()
            } catch (e: Exception) {
                _rifas.value = emptyList() // o manejar error
            }
        }
    }

    fun buscarRifas(query: String) {
        viewModelScope.launch {
            try {
                _rifas.value = repository.buscar(query)
            } catch (e: Exception) {
                _rifas.value = emptyList()
            }
        }
    }
}