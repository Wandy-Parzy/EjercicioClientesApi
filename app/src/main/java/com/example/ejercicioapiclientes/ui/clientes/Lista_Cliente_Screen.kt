package com.example.ejercicioapiclientes.ui.clientes

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ejemploapi.ui.navigation.ScreenModule
import com.example.ejercicioapiclientes.data.web.remote.dto.ClientesDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Lista_Cliente_Screen(
    navController: NavController,
    viewModel: ClientesApiViewModel = hiltViewModel(),
    onClientesClick: (Int) -> Unit
) {
    Column( modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.padding(20.dp))
        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            "Lista de Clientes", fontSize = 30.sp,
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { navController.navigate(ScreenModule.Nuevo_Cliente.route) }
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Save")
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) {
            val uiState by viewModel.uiState.collectAsState()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            )
            {
                ClientesListBody(uiState.clientes) {
                    onClientesClick(it)
                }
            }
        }
    }

}

@Composable
fun ClientesListBody(clientesList: List<ClientesDto>, onClientesClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(clientesList) {clientes ->
                ClientesRow(clientes)
                {
                    onClientesClick(it)
                }
            }
        }
    }
}

@Composable
fun ClientesRow(clientes: ClientesDto, onClientesClick: (Int) -> Unit) {

    val viewModel: ClientesApiViewModel = hiltViewModel()
    val navController = rememberNavController()

    Spacer(modifier = Modifier.padding(5.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .wrapContentSize(Alignment.Center)
                .border(
                    2.dp, Color(0xA88E24AA),
                    shape = RoundedCornerShape(5.dp)
                )
        ) {
            Card(
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                        .clickable(onClick = { clientes.clienteId?.let { onClientesClick(it) } })
                ) {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.CenterEnd)
                    ) {
                        Text(
                            text = clientes.nombres,
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color(0xC3303030),
                            modifier = Modifier.weight(8f)
                        )
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.TopStart),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            text = clientes.rnc,
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color(0xC3303030),
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = clientes.direccion.toString(),
                            fontSize = 20.sp,
                            style = MaterialTheme.typography.titleSmall,
                            color = Color(0xC3303030),
                            modifier = Modifier.weight(8f)
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.TopCenter)
        ) {}
    }
}