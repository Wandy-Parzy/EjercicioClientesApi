package com.example.ejercicioapiclientes.data.repository

import com.example.ejercicioapiclientes.data.web.ClientesApi
import com.example.ejercicioapiclientes.data.web.remote.dto.ClientesDto
import com.example.ejercicioapiclientes.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ClientesApiRepositoryImp @Inject constructor(
    private val clientesApi: ClientesApi
): ClientesApiRepository {

    override fun getClientes(): Flow<Resource<List<ClientesDto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val cliente = clientesApi.getClientes() //descarga los tickets de internet, se supone quedemora algo

            emit(Resource.Success(cliente)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
    override fun getClientesId(id: Int): Flow<Resource<ClientesDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val clientes =
                clientesApi.getClientesId(id) //descagar la lista de tickets por el id

            emit(Resource.Success(clientes)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    override suspend fun putClientes(id: Int, clientesDto: ClientesDto) {
        clientesApi.putClientes(id, clientesDto)
    }
    override suspend fun deleteClientes(id: Int){
        clientesApi.deleteClientes(id)
    }
    override suspend fun postClientes(clientesDto: ClientesDto) {
        clientesApi.postClientes(clientesDto)
    }
}