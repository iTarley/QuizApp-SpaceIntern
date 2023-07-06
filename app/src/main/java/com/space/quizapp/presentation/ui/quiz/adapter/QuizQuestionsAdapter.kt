package com.space.quizapp.presentation.ui.quiz.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.utils.extensions.viewBinding

class QuizQuestionsAdapter :
    BaseListAdapter<QuizQuestionUIModel.Answer, ItemAnswerBinding>(MyItemDiffCallback()) {

    override fun createBinding(parent: ViewGroup): ItemAnswerBinding {
        return parent.viewBinding(ItemAnswerBinding::inflate)
    }

    override fun onBind(
        binding: ItemAnswerBinding,
        item: QuizQuestionUIModel.Answer,
        onClickCallback: ((QuizQuestionUIModel.Answer) -> Unit)?
    ) {

        binding.optionTitleTextView.text = item.answer


        setListener(binding.root) {
            onClickCallback?.invoke(item)
        }
    }

    private fun setListener(view: View, onItemClick: () -> Unit) {
        view.setOnClickListener {
            onItemClick()
        }
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