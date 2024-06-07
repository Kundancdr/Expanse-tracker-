package com.example.expansetrack

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavHostScreen() {
    val navControler = rememberNavController()
   NavHost(navController = navControler, startDestination = "/home" ){
       composable(route ="/home"){
           HomeScreen(navControler)
       }
       composable(route ="/add"){
           AddExpense(navControler)
       }
   }

}