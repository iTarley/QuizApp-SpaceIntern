package com.space.quizapp.presentation.ui.quiz.adapter.manager

import android.view.View
import androidx.viewbinding.ViewBinding
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.ui.quiz.adapter.QuizQuestionsAdapter
import com.space.quizapp.utils.extensions.setTextColorCompat
import com.space.quizapp.utils.extensions.setTint

class QuizManager(private val adapter: QuizQuestionsAdapter) {

    fun handleAnswerClick(binding: ItemAnswerBinding, item: QuizQuestionUIModel.Answer,position:Int) {
        val correctAnswer = item.correctAnswer
        val lastQuestion = item.lastQuestion
        val plusPoint = item.point
        val correctBinding = adapter.getItemBinding(correctAnswer)
        binding.root.setTint(R.color.neutral_light_gray)
        adapter.clickable =false
        if (item.correctAnswer == position) {
            handleCorrectAnswer(binding,lastQuestion,plusPoint)
        } else {
            handleWrongAnswer(binding, correctBinding,lastQuestion)
        }
    }

    private fun handleCorrectAnswer(binding: ItemAnswerBinding,lastQuestion:Boolean,plusPoint:Int) {
        adapter.points += plusPoint
        with(binding){
            answerCardView.setTint(R.color.success_green)
            correctPointTextView.apply {
                visibility = View.VISIBLE
                text = "+$plusPoint"
            }
            optionTitleTextView.setTextColorCompat(R.color.neutral_white)

        }

        if (lastQuestion) {
            adapter.lastQuestion = true
        }else{
            adapter.quizId++
        }
    }

    private fun handleWrongAnswer(binding: ItemAnswerBinding, correctBinding: ItemAnswerBinding?,lastQuestion:Boolean) {
        with(binding){
            answerCardView.setTint(R.color.wrong_red)
            correctPointTextView.visibility = View.GONE
            optionTitleTextView.setTextColorCompat(R.color.neutral_white)
        }
        correctBinding?.root?.setTint(R.color.success_green)
        correctBinding?.optionTitleTextView?.setTextColorCompat(R.color.neutral_white)

        if (lastQuestion) {
            adapter.lastQuestion = true
        }else{
            adapter.quizId++
        }
    }
}