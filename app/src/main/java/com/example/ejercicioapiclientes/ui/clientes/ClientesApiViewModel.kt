package com.example.ejercicioapiclientes.ui.clientes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicioapiclientes.data.repository.ClientesApiRepositoryImp
import com.example.ejercicioapiclientes.data.web.remote.dto.ClientesDto
import com.example.ejercicioapiclientes.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ClientesListState(
    val isLoading: Boolean = false,
    val clientes: List<ClientesDto> = emptyList(),
    val error: String = ""
)
data class ClientesState(
    val isLoading: Boolean = false,
    val cliente: ClientesDto? = null,
    val error: String = ""
)
@HiltViewModel
class ClientesApiViewModel @Inject constructor(
    private val clientesRepository: ClientesApiRepositoryImp

    ) : ViewModel() {
    var nombre by mutableStateOf("")
    var nombreError by mutableStateOf("")

    var rnc by mutableStateOf("")
    var rncError by mutableStateOf("")

    var direccion by mutableStateOf("")
    var direccionError by mutableStateOf("")

    var limiteCredito by mutableStateOf(0)
    var limiteCreditoError by mutableStateOf("")

    var clienteId by mutableStateOf(0)

    var uiState = MutableStateFlow(ClientesListState())
        private set
    var uiStateClientes = MutableStateFlow(ClientesState())
        private set

    init {
        clientesRepository.getClientes().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiState.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiState.update {
                        it.copy(clientes = result.data ?: emptyList())
                    }
                }
                is Resource.Error -> {
                    uiState.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun ClientesbyId(id: Int) {
        clienteId = id
        Limpiar()
        clientesRepository.getClientesId(clienteId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    uiStateClientes.update { it.copy(isLoading = true) }
                }
                is Resource.Success -> {
                    uiStateClientes.update {
                        it.copy(cliente = result.data)
                    }
                    nombre = uiStateClientes.value.cliente!!.nombre
                    rnc = uiStateClientes.value.cliente!!.rnc
                    direccion = uiStateClientes.value.cliente!!.direccion
                    limiteCredito = uiStateClientes.value.cliente!!.limiteCredito

                }
                is Resource.Error -> {
                    uiStateClientes.update { it.copy(error = result.message ?: "Error desconocido") }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun putClientes(id: Int) {
        viewModelScope.launch {
            clienteId = id
            try {
                if (clienteId > 0) {
                    clientesRepository.putClientes(
                        clienteId, ClientesDto(
                            clienteId = clienteId,
                            nombre = nombre,
                            rnc = rnc,
                            direccion = direccion,
                            limiteCredito = limiteCredito
                        )
                    )
                } else {
                    throw NullPointerException("Value is null")
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    fun onNombreChanged(nombre: String) {
        this.nombre = nombre
        HayErrores()
    }

    fun onRncChanged(rnc: String) {
        this.rnc = rnc
        HayErrores()
    }

    fun onDireccionChanged(direccion: String) {
        this.direccion = direccion
        HayErrores()
    }

    fun HayErrores(): Boolean {
        var hayError = false
        nombreError = ""
        if (nombre.isBlank()) {
            nombreError = "Ingrese el nombre"
            hayError = true
        }

        rncError = ""
        if (rnc.isBlank()) {
            rncError = "Ingrese el rnc"
            hayError = true
        }

        direccionError = ""
        if(direccion.isBlank()){
            direccionError = "Ingrese la direccion"
            hayError = true
        }

        limiteCreditoError = ""
        if (limiteCredito == 0) {
            limiteCreditoError = "Ingrese el limite de credito"
            hayError = true
        }
        return hayError
    }

    fun deleteClientes(id: Int) {
        viewModelScope.launch {
            clienteId = id
            try {
                if (clienteId != null) {
                    clientesRepository.deleteClientes(clienteId)
                } else {
                    throw NullPointerException("Value is null")
                }
            } catch (e: NullPointerException) {
                e.printStackTrace()
            }
        }
    }

    fun postClientes() {
        viewModelScope.launch {
            if (clienteId == null) {
                clienteId += 1
            }
            if (limiteCredito == 0) {
                limiteCredito += 5
            }
            clientesRepository.postClientes(
                ClientesDto(
                    clienteId = clienteId,
                    nombre = nombre,
                    rnc = rnc,
                    direccion = direccion,
                    limiteCredito = limiteCredito
                )
            )
        }
    }

    private fun Limpiar() {
        nombre = ""
        rnc = ""
        direccion = ""
        limiteCredito = 0
    }
}
