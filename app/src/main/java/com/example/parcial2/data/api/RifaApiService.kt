package com.example.parcial2.data.api

import com.example.parcial2.data.Modelo.Rifa
import retrofit2.Response
import retrofit2.http.*

interface RifaApiService {
    @GET("rifas")
    suspend fun getRifas(): List<Rifa>

    @GET("rifas/{id}")
    suspend fun getRifa(@Path("id") id: String): Rifa

    @POST("rifas")
    suspend fun crearRifa(@Body rifa: Rifa): Rifa

    @PUT("rifas/{id}")
    suspend fun actualizarRifa(@Path("id") id: String, @Body rifa: Rifa): Rifa

    @DELETE("rifas/{id}")
    suspend fun eliminarRifa(@Path("id") id: String): Response<Unit>
}