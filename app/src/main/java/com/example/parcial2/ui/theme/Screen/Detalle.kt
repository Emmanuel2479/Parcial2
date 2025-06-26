package com.example.parcial2.ui.theme.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.data.Modelo.Rifa
import com.example.parcial2.viewmodel.DetalleViewModel
import kotlinx.coroutines.launch

@Composable
fun DetalleScreen(navController: NavController, viewModel: DetalleViewModel, rifaId: String) {
    val scope = rememberCoroutineScope()
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var boletoGanador by remember { mutableStateOf(TextFieldValue("")) }
    var estaHabilitada by remember { mutableStateOf(true) }
    var numerosSeleccionados by remember { mutableStateOf(listOf<Int>()) }

    val numerosInactivos = listOf(1, 17, 31, 53, 88) // opcional, si quieres mantener bloqueos

    LaunchedEffect(rifaId) {
        scope.launch {
            val rifa = viewModel.obtenerRifaPorId(rifaId)
            rifa?.let {
                nombre = it.nombre
                fecha = it.fecha
                boletoGanador = TextFieldValue(it.boletoGanador)
                estaHabilitada = it.habilitada
                numerosSeleccionados = it.numerosSeleccionados
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Rifa: $nombre", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Fecha: $fecha", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(10),
            modifier = Modifier
                .height(300.dp)
                .padding(8.dp)
        ) {
            items((1..99).toList()) { numero ->
                val numeroStr = numero.toString().padStart(2, '0')
                val isInactive = numero in numerosInactivos
                val isSelected = numero in numerosSeleccionados

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(32.dp)
                        .padding(2.dp)
                        .background(
                            color = when {
                                isInactive -> Color(0xFFE1BEE7)
                                isSelected -> Color(0xFFFFCDD2)
                                else -> Color.White
                            },
                            shape = MaterialTheme.shapes.small
                        )
                        .border(1.dp, Color.Gray, MaterialTheme.shapes.small)
                        .clickable(enabled = !isInactive) {
                            numerosSeleccionados = if (isSelected) {
                                numerosSeleccionados - numero
                            } else {
                                numerosSeleccionados + numero
                            }
                        }
                ) {
                    Text(text = numeroStr, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Boleto ganador")
        TextField(
            value = boletoGanador,
            onValueChange = { boletoGanador = it },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("¿Rifa inhabilitada?")
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = !estaHabilitada,
                onCheckedChange = { estaHabilitada = !it }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    val actualizada = Rifa(
                        id = rifaId,
                        nombre = nombre,
                        fecha = fecha,
                        boletoGanador = boletoGanador.text,
                        numerosSeleccionados = numerosSeleccionados,
                        habilitada = estaHabilitada
                    )
                    viewModel.guardarCambios(rifaId, actualizada)
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Guardar")
            }

            Button(
                onClick = {
                    scope.launch {
                        viewModel.eliminarRifa(rifaId)
                        navController.popBackStack()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5A5F))
            ) {
                Text("Eliminar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver")
        }
    }
}

