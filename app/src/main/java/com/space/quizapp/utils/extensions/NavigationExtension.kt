package com.space.quizapp.utils.extensions

import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation

fun navigateSafe(navController: NavController, action: NavDirections) {
    navController.currentDestination?.getAction(action.actionId)?.let {
        navController.navigate(action)
    }
}
fun popBackStack(it: View){
    Navigation.findNavController(it).popBackStack()
}
