package com.space.quizapp.presentation.quiz_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.space.quizapp.R
import com.space.quizapp.presentation.ui.home.HomeFragment
import com.space.quizapp.presentation.ui.quiz.QuizFragment
import com.space.quizapp.presentation.ui.start.StartFragment

class QuizActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        supportActionBar?.hide()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView)
                as NavHostFragment
        navController = navHostFragment.navController
    }
}