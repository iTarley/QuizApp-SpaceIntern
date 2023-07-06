package com.space.quizapp.presentation.ui.quiz.adapter.manager

import android.view.View
import androidx.viewbinding.ViewBinding
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.ui.quiz.adapter.QuizQuestionsAdapter
import com.space.quizapp.utils.extensions.setTint

class QuizManager(private val adapter: QuizQuestionsAdapter) {

    fun handleAnswerClick(binding: ItemAnswerBinding, item: QuizQuestionUIModel.Answer,position:Int) {
        val correctAnswer = item.correctAnswer
        val lastQuestion = item.lastQuestion
        val plusPoint = item.point
        val correctBinding = adapter.getItemBinding(correctAnswer)

        binding.root.setTint(R.color.neutral_light_gray)

        if (item.correctAnswer == position) {
            handleCorrectAnswer(binding,lastQuestion,plusPoint)
        } else {
            handleWrongAnswer(binding, correctBinding,lastQuestion)
        }
    }


    private fun handleCorrectAnswer(binding: ItemAnswerBinding,lastQuestion:Boolean,plusPoint:Int) {
        adapter.points += plusPoint
        binding.answerCardView.setTint(R.color.success_green)
        binding.correctPointTextView.visibility = View.VISIBLE
        if (lastQuestion) {
            adapter.lastQuestion = true
        }else{
            adapter.quizId++
        }
    }

    private fun handleWrongAnswer(binding: ItemAnswerBinding, correctBinding: ViewBinding?,lastQuestion:Boolean) {
        binding.answerCardView.setTint(R.color.wrong_red)
        binding.correctPointTextView.visibility = View.GONE

        correctBinding?.root?.setTint(R.color.success_green)

        if (lastQuestion) {
            adapter.lastQuestion = true
        }else{
            adapter.quizId++
        }
    }
}