package com.example.parcial2.data.Repository

import com.example.parcial2.data.Modelo.Rifa
import kotlinx.coroutines.delay

class RifaRepository {
    private val rifas = mutableListOf(
        Rifa(1, "Computador", "01/07/2025"),
        Rifa(2, "Bono", "02/07/2025"),
        Rifa(3, "Televisor", "03/07/2025")
    )

    suspend fun obtenerTodas(): List<Rifa> {
        delay(300) // simula red
        return rifas
    }

    suspend fun buscar(query: String): List<Rifa> {
        delay(300)
        return rifas.filter {
            it.nombre.contains(query, ignoreCase = true) ||
                    it.fecha.contains(query, ignoreCase = true)
        }
    }

    suspend fun crear(rifa: Rifa) {
        delay(300)
        val nuevoId = (rifas.maxOfOrNull { it.id } ?: 0) + 1
        rifas.add(rifa.copy(id = nuevoId))
    }

    suspend fun obtenerPorId(id: Int): Rifa? {
        delay(300)
        return rifas.find { it.id == id }
    }

    suspend fun eliminar(id: Int) {
        delay(300)
        rifas.removeIf { it.id == id }
    }
}