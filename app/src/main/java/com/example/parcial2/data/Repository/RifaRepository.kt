package com.example.parcial2.data.Repository

import com.example.parcial2.data.Modelo.Rifa
import com.example.parcial2.data.api.RetrofitInstance

class RifaRepository {
    private val api = RetrofitInstance.api

    suspend fun obtenerTodas(): List<Rifa> = api.getRifas()

    suspend fun buscar(query: String): List<Rifa> {
        return api.getRifas().filter {
            it.nombre.contains(query, ignoreCase = true) ||
                    it.fecha.contains(query, ignoreCase = true)
        }
    }

    suspend fun crear(rifa: Rifa): Rifa = api.crearRifa(rifa)

    suspend fun obtenerPorId(id: String): Rifa = api.getRifa(id)

    suspend fun actualizar(id: String, rifa: Rifa): Rifa = api.actualizarRifa(id, rifa)

    suspend fun eliminar(id: String) = api.eliminarRifa(id)
}