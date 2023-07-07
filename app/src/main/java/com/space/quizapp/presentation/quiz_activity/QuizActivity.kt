package com.space.quizapp.presentation.quiz_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.space.quizapp.R
import org.koin.android.ext.android.getKoin
import org.koin.dsl.module

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_quiz)
        supportActionBar?.hide()
        val activityModule = module {
            single<NavController> { findNavController(R.id.fragmentContainerView) }
        }
        getKoin().loadModules(listOf(activityModule))
    }
}