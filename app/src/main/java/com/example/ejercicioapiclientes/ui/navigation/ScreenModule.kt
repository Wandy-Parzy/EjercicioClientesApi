package com.example.ejemploapi.ui.navigation

sealed class ScreenModule(val route: String) {
    object Cliente : ScreenModule("clientes")
    object Nuevo_Cliente : ScreenModule("nuevo_cliente")
    object Lista_Cliente : ScreenModule("lista_clientes")
}