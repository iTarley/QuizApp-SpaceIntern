package com.space.quizapp.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

fun Fragment.navigateSafe(action: NavDirections) {
    findNavController().currentDestination?.getAction(action.actionId)?.let {
        findNavController().navigate(action)
    }
}
fun popBackStack(it: View){
    Navigation.findNavController(it).popBackStack()
}