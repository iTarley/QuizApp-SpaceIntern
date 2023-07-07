package com.space.quizapi

import android.os.Bundle

interface QuizFeatureNavigator {
    fun navigateToSecondFragment(argument: Bundle)
    fun navigateUp()
}