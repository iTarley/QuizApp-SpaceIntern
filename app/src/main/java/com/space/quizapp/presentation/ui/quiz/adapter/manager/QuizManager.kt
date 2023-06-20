package com.space.quizapp.presentation.ui.quiz.adapter.manager

import android.view.View
import androidx.viewbinding.ViewBinding
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.ui.quiz.adapter.QuizQuestionsAdapter
import com.space.quizapp.utils.extensions.setTint

class QuizManager(private val adapter: QuizQuestionsAdapter) {
    private fun lastQuestion(): Boolean {
        val quizSize = adapter.quiz?.size ?: 0
        return adapter.quizId >= quizSize - 1
    }

    fun handleAnswerClick(binding: ItemAnswerBinding, item: QuizQuestionUIModel.Answer) {
        val currentQuestion = adapter.quiz?.get(adapter.quizId) ?: return
        val correctAnswer = currentQuestion.correctAnswer
        val correctInAnswersIndex = currentQuestion.data.indexOfFirst { it.answer == correctAnswer }
        val correctBinding = adapter.getItemBinding(correctInAnswersIndex)

        binding.root.setTint(R.color.neutral_light_gray)

        if (item.answer == correctAnswer) {
            handleCorrectAnswer(binding)
        } else {
            handleWrongAnswer(binding, correctBinding)
        }
    }

    private fun handleCorrectAnswer(binding: ItemAnswerBinding) {
        adapter.points++
        binding.answerCardView.setTint(R.color.success_green)
        binding.correctPointTextView.visibility = View.VISIBLE
        adapter.clickable = false

        if (lastQuestion()) {
            adapter.lastQuestion = true
        } else {
            adapter.quizId++
        }
    }

    private fun handleWrongAnswer(binding: ItemAnswerBinding, correctBinding: ViewBinding?) {
        binding.answerCardView.setTint(R.color.wrong_red)
        binding.correctPointTextView.visibility = View.GONE

        correctBinding?.root?.setTint(R.color.success_green)
        adapter.clickable = false

        if (lastQuestion()) {
            adapter.lastQuestion = true
        } else {
            adapter.quizId++
        }
    }
}