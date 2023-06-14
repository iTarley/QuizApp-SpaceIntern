package com.space.quizapp.presentation.ui.quiz.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.utils.extensions.setTint
import com.space.quizapp.utils.extensions.viewBinding

class QuizQuestionsAdapter :
    BaseListAdapter<QuizQuestionUIModel.Answer, ItemAnswerBinding>(MyItemDiffCallback()) {

    private var quiz: List<QuizQuestionUIModel>? = listOf()
    var quizId = 0
    var quizState = QuizState.IN_PROGRESS

    override fun createBinding(parent: ViewGroup): ItemAnswerBinding {
        return parent.viewBinding(ItemAnswerBinding::inflate)
    }

    fun setQuiz(quiz: List<QuizQuestionUIModel>?) {
        this.quiz = quiz
        notifyDataSetChanged()
    }

    private fun isQuizFinished(): Boolean {
        return quizId >= quiz!!.size - 1
    }

    override fun onBind(
        binding: ItemAnswerBinding,
        item: QuizQuestionUIModel.Answer,
        position:Int,
        onClickCallback: ((QuizQuestionUIModel.Answer) -> Unit)?
    ) {
        binding.optionTitleTextView.text = item.answer
        binding.answerCardView.setTint(R.color.neutral_light_gray)
        binding.correctPointTextView.visibility = View.GONE

        setListener(binding.answerCardView){
                onClickCallback?.invoke(item)
                handleAnswerClick(binding, item)
        }
    }

    private fun setListener(view: View, onItemClick: () -> Unit) {
        view.setOnClickListener {
            onItemClick()
        }
    }

    private fun handleAnswerClick(binding: ItemAnswerBinding, item: QuizQuestionUIModel.Answer) {
        if (item.answer == quiz!![quizId].correctAnswer) {
            if (isQuizFinished()) {
                quizState = QuizState.FINISHED
            } else {
                quizId++
            }
            binding.answerCardView.setTint(R.color.success_green)
            binding.correctPointTextView.visibility = View.VISIBLE
        } else {
            binding.answerCardView.setTint(R.color.wrong_red)
            binding.correctPointTextView.visibility = View.GONE
        }
        binding.root.isClickable = false
    }

    private class MyItemDiffCallback : DiffUtil.ItemCallback<QuizQuestionUIModel.Answer>() {

        override fun areItemsTheSame(
            oldItem: QuizQuestionUIModel.Answer,
            newItem: QuizQuestionUIModel.Answer
        ): Boolean {
            // Compare based on unique identifiers of the items
            return oldItem.answer == newItem.answer
        }

        override fun areContentsTheSame(
            oldItem: QuizQuestionUIModel.Answer,
            newItem: QuizQuestionUIModel.Answer
        ): Boolean {
            // Compare based on the actual content of the items
            return oldItem == newItem
        }
    }
}