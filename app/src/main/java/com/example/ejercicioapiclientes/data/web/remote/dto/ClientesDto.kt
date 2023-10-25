package com.example.ejercicioapiclientes.data.web.remote.dto

data class ClientesDto(
    val clienteId: Int,
    val nombre: String,
    val rnc: String,
    val direccion: String,
    val limiteCredito: Int
)