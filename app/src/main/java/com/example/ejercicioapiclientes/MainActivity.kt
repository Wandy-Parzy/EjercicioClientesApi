package com.example.ejercicioapiclientes

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.ejemploapi.ui.clientes.ClienteScreen
import com.example.ejemploapi.ui.clientes.Lista_Cliente_Screen
import com.example.ejemploapi.ui.clientes.NuevoClienteScreen
import com.example.ejemploapi.ui.navigation.ScreenModule
import com.example.ejercicioapiclientes.ui.theme.EjercicioApiClientesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjercicioApiClientesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenModule.Lista_Cliente.route
                    ) {
                        composable(ScreenModule.Nuevo_Cliente.route) {
                            NuevoClienteScreen(navController = navController) {
                                navController.navigate(ScreenModule.Nuevo_Cliente.route)
                            }
                        }

                        composable(ScreenModule.Lista_Cliente.route) {
                            Lista_Cliente_Screen(navController = navController) { id ->
                                navController.navigate(ScreenModule.Cliente.route + "/${id}")
                            }
                        }

                        composable(
                            ScreenModule.Cliente.route + "/{id}",
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { capturar ->
                            var clienteId = capturar.arguments?.getInt("id") ?: 0

                            ClienteScreen(clienteId = clienteId, navController = navController){
                                navController.navigate(ScreenModule.Lista_Cliente.route)
                            }
                        }
                    }
                }
            }
        }
    }
}

