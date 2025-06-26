package com.example.parcial2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial2.data.Repository.RifaRepository
import com.example.parcial2.ui.theme.Screen.DetalleScreen
import com.example.parcial2.ui.theme.Screen.ListaScreen
import com.example.parcial2.ui.theme.Screen.PujarScreen
import com.example.parcial2.viewmodel.DetalleViewModel
import com.example.parcial2.viewmodel.ListaViewModel
import com.example.parcial2.viewmodel.PujarViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.parcial2.viewmodel.ViewModelFactory
@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    val repository = RifaRepository()

    val factory = ViewModelFactory(repository)

    val listaViewModel: ListaViewModel = viewModel(factory = factory)
    val pujarViewModel: PujarViewModel = viewModel(factory = factory)
    val detalleViewModel: DetalleViewModel = viewModel(factory = factory)


    NavHost(navController = navController, startDestination = "lista") {
        composable("lista") {
            ListaScreen(navController, listaViewModel)
        }
        composable("pujar") {
            PujarScreen(navController, pujarViewModel)
        }
        composable("detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            if (id != null) {
                DetalleScreen(navController, detalleViewModel, id)
            }
        }
    }
}
