package com.example.parcial2.ui.theme.Screen

import android.app.DatePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.parcial2.viewmodel.PujarViewModel
import java.util.*

@Composable
fun PujarScreen(navController: NavController, viewModel: PujarViewModel) {
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, yearSelected, monthSelected, dayOfMonth ->
                fecha = String.format("%02d/%02d/%04d", dayOfMonth, monthSelected + 1, yearSelected)
            },
            year, month, day
        )
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha de la rifa") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() },
            enabled = false,
            readOnly = true,
            placeholder = { Text("Seleccionar fecha") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.crearRifa(nombre, fecha)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        ) {
            Text("Volver")
        }
    }
}