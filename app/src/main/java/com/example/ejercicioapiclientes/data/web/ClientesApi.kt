package com.example.ejercicioapiclientes.data.web

import com.example.ejercicioapiclientes.data.web.remote.dto.ClientesDto
import retrofit2.Response
import retrofit2.http.*

interface ClientesApi{
    @GET("/api/clientes")
    suspend fun getClientes(): List<ClientesDto>

    @GET("/api/clientes/{id}")
    suspend fun getClientesId(@Path("id") id: Int): ClientesDto

    @POST("/api/clientes")
    suspend fun postClientes(@Body clientesDto: ClientesDto): ClientesDto

    @PUT("/api/clientes/{id}")
    suspend fun putClientes(@Path("id") id: Int, @Body clientesDto: ClientesDto): Response<Unit>

    @DELETE("/api/clientes/{id}")
    suspend fun deleteClientes(@Path("id") id: Int): ClientesDto
}