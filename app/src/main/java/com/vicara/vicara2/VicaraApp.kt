package com.vicara.vicara2

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vicara.vicara2.ui.navigation.Screen
import com.vicara.vicara2.ui.screen.addkartu.AddKartuScreen
import com.vicara.vicara2.ui.screen.edit.EditKartuScreen
import com.vicara.vicara2.ui.screen.home.HomeScreen
import com.vicara.vicara2.ui.screen.koleksikartu.KoleksiKartuScreen
import com.vicara.vicara2.ui.screen.susunkartu.SusunKartuScreen

@Composable
fun VicaraApp(
    application: Application,
    modifier : Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
){
    Scaffold(
        backgroundColor = Color.Transparent,
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route){}
            composable(Screen.Home.route){
                HomeScreen(
                    toAddkartu = {
                        navController.navigate(Screen.AddKartu.route)
                    },
                    toKoleksiKartu = {
                        navController.navigate(Screen.KoleksiKartu.route)
                    },
                    toSusunKartu = {
                        navController.navigate(Screen.SusunKartu.route)
                    })
            }
            composable(Screen.AddKartu.route){
                AddKartuScreen(application, backToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(navController.graph.id)
                    }
                })
            }
            composable(Screen.KoleksiKartu.route){
                KoleksiKartuScreen(editKartu = {
                    navController.navigate(Screen.EditKartu.createRoute(it))
                })
            }
            composable(Screen.SusunKartu.route){
                SusunKartuScreen()
            }
            composable(
                route = Screen.EditKartu.route,
                arguments = listOf(navArgument("kartuId"){
                    type = NavType.IntType
                })
            ) {
                val kartuId = it.arguments?.getInt("kartuId") ?: 0
                EditKartuScreen(id = kartuId, application = application, backToKoleksi = {
                    navController.navigateUp()
                })
            }
        }

    }
}