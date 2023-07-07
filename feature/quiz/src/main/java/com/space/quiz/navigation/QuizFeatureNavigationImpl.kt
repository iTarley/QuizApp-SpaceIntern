package com.space.quiz.navigation

import android.os.Bundle
import android.util.Log
import com.space.navigationapi.AppNavigation
import com.space.quizapi.QuizFeatureNavigator

class QuizFeatureNavigationImpl(private val appNavigator: AppNavigation):QuizFeatureNavigator {
    override fun navigateToSecondFragment(argument: Bundle) {
        appNavigator.navigateFirstFeatureToSecondFeature(argument)
    }

    override fun navigateUp() {
       appNavigator.navigateUp()
    }
}