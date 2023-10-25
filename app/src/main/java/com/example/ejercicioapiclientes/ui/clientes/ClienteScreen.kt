package com.example.ejemploapi.ui.clientes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.twotone.Check
import androidx.compose.material.icons.twotone.Close
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.example.ejercicioapiclientes.ui.clientes.ClientesApiViewModel
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ClienteScreen(
    navController : NavController,
    clienteId: Int,
    viewModel: ClientesApiViewModel = hiltViewModel(),
    onSaveClick: () -> Unit
) {
    remember {
        viewModel.ClientesbyId(clienteId)
        0
    }
    /*----------------------------------------Code Start------------------------------------------------------*/

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterEnd)
    ) {

        Spacer(Modifier.height(20.dp))

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de Clientes", fontSize = 27.sp,
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
            label = { Text(text = "Nombre") },
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
                    viewModel.putClientes(clienteId)
                    onSaveClick()
                }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .size(60.dp, 50.dp)
                    .wrapContentSize(Alignment.Center),
                text = {},
                containerColor = Color.Red,
                contentColor = Color.Red,
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "delete",
                        tint = Color.White
                    )
                },
                onClick = {
                    viewModel.deleteClientes(clienteId)
                    onSaveClick()
                }
            )
        }
    }
}

