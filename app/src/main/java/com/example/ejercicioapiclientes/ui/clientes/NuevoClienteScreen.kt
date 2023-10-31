package com.example.ejercicioapiclientes.ui.clientes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.twotone.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.ejemploapi.ui.navigation.ScreenModule
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NuevoClienteScreen(
    navController: NavController,
    viewModel: ClientesApiViewModel = hiltViewModel(),
    onSaveClick: () -> Unit
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterEnd)
    ) {
        Icon(
            imageVector = Icons.TwoTone.ArrowBack,
            contentDescription = null,
            tint = Color(0xCD8595FF),
            modifier = Modifier
                .align(Alignment.Start)
                .size(50.dp, 50.dp)
                .clickable {
                    scope.launch {
                        navController.navigate(ScreenModule.Lista_Cliente.route)
                    }
                }
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de Nuevo de clientes", fontSize = 27.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .wrapContentSize(Alignment.Center),
            value = viewModel.nombre,
            label = { Text(text = "Nombre del cliente") },
            onValueChange = viewModel::onNombreChanged,
            isError = viewModel.nombreError.isNotBlank(),
            trailingIcon = {
                if (viewModel.nombreError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Close, contentDescription = "error")
                } else {
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
                }
            }
        )
        if (viewModel.nombreError.isNotBlank()) {
            Text(
                text = viewModel.nombreError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.nombreError,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .wrapContentSize(Alignment.Center),
            value = viewModel.rnc,
            label = { Text(text = "RNC") },
            onValueChange = viewModel::onRncChanged,
            isError = viewModel.rncError.isNotBlank(),
            trailingIcon = {
                if (viewModel.rncError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Close, contentDescription = "error")
                } else {
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
                }
            }
        )
        if (viewModel.rncError.isNotBlank()) {
            Text(
                text = viewModel.rncError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.rncError,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .wrapContentSize(Alignment.Center),
            value = viewModel.direccion,
            label = { Text(text = "Direccion") },
            onValueChange = viewModel::onDireccionChanged,
            isError = viewModel.direccionError.isNotBlank(),
            trailingIcon = {
                if (viewModel.direccionError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Close, contentDescription = "error")
                } else {
                    Icon(imageVector = Icons.TwoTone.Check, contentDescription = "success")
                }
            }
        )
        if (viewModel.direccionError.isNotBlank()) {
            Text(
                text = viewModel.direccionError,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .align(Alignment.CenterHorizontally)
            )
        } else {
            Text(
                text = viewModel.direccionError,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.wrapContentSize(Alignment.Center)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(60.dp, 50.dp)
                    .wrapContentSize(Alignment.Center),
                text = {},
                containerColor = Color.Blue,
                contentColor = Color.Blue,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Save",
                        tint = Color.White
                    )
                },
                onClick = {
                    viewModel.postClientes()
                    navController.navigate(ScreenModule.Lista_Cliente.route)
                    onSaveClick()
                }
            )
        }
    }
}


