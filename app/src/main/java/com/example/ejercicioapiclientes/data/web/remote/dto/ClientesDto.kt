package com.example.ejercicioapiclientes.data.web.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clientes")
data class ClientesDto (
    @PrimaryKey
    var clienteId : Int?=null,
    var nombres: String = "",
    var rnc: String = "",
    var direccion: String? = "",
    var limiteCredito: Int = 0
)