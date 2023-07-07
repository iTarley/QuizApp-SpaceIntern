package com.space.quizapp.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.space.navigationapi.AppNavigation
import com.space.quizapp.R

class AppNavigationImpl(private val navController: NavController): AppNavigation {
    override fun navigateFirstFeatureToSecondFeature(arg: Bundle) {
        navController.navigate(R.id.action_quizHomeFragment_to_quizFragment)
    }

    override fun navigateUp() {
        navController.navigateUp()
    }
}