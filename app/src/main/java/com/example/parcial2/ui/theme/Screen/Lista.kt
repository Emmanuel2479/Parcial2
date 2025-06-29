package com.example.parcial2.ui.theme.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.data.Modelo.Rifa
import com.example.parcial2.viewmodel.ListaViewModel

@Composable
fun ListaScreen(navController: NavController, viewModel: ListaViewModel) {
    var query by remember { mutableStateOf("") }
    val rifas by viewModel.rifas.collectAsState()

    LaunchedEffect(query) {
        viewModel.buscarRifas(query)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Buscar por nombre o fecha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("pujar") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nueva")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(rifas.size) { i ->
                val rifa = rifas[i]
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable {
                            navController.navigate("detalle/${rifa.id}")
                        }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Nombre: ${rifa.nombre}")
                        Text("Fecha: ${rifa.fecha}")
                    }
                }
            }
        }
    }
}
