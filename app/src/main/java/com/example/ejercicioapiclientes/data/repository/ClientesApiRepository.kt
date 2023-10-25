package com.example.ejercicioapiclientes.data.repository

import com.example.ejercicioapiclientes.data.web.remote.dto.ClientesDto
import com.example.ejercicioapiclientes.util.Resource
import kotlinx.coroutines.flow.Flow


interface ClientesApiRepository
{
    fun getClientes(): Flow<Resource<List<ClientesDto>>>
    fun getClientesId(id: Int): Flow<Resource<ClientesDto>>
    suspend fun putClientes(id: Int, clientesDto: ClientesDto)
    suspend fun deleteClientes(id: Int)
    suspend fun postClientes(clientesDto: ClientesDto)
}