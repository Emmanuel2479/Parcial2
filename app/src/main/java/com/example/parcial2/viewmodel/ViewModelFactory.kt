package com.example.parcial2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.parcial2.data.Repository.RifaRepository

class ViewModelFactory(private val repository: RifaRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListaViewModel::class.java) -> ListaViewModel(repository)
            modelClass.isAssignableFrom(PujarViewModel::class.java) -> PujarViewModel(repository)
            modelClass.isAssignableFrom(DetalleViewModel::class.java) -> DetalleViewModel(repository)
            else -> throw IllegalArgumentException("ViewModel desconocido: ${modelClass.name}")
        } as T
    }
}
