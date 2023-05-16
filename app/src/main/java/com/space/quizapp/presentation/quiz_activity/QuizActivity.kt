package com.space.quizapp.presentation.quiz_activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.quizapp.R
import com.space.quizapp.presentation.ui.home.HomeFragment
import com.space.quizapp.presentation.ui.quiz.QuizFragment
import com.space.quizapp.presentation.ui.start.StartFragment

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        supportActionBar?.hide()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, QuizFragment.newInstance())
                .commitNow()
        }
    }
}