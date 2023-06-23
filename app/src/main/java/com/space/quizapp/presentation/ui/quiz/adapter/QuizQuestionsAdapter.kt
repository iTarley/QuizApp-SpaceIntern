package com.space.quizapp.presentation.ui.quiz.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.presentation.ui.base.adapter.BaseViewHolder
import com.space.quizapp.presentation.ui.quiz.adapter.manager.QuizManager
import com.space.quizapp.utils.extensions.setTint

class QuizQuestionsAdapter() :
    BaseListAdapter<QuizQuestionUIModel.Answer, ItemAnswerBinding>(MyItemDiffCallback()) {

    var quizId = 0
    var points = 0
    var lastQuestion = false
    var clickable = true
    private var recyclerView: RecyclerView? = null
    private var quizManager: QuizManager = QuizManager(this)


    fun getItemBinding(position: Int): ViewBinding? {
        val viewHolder = recyclerView!!.findViewHolderForAdapterPosition(position)
        return viewHolder?.let {
            (it as? BaseViewHolder<*>)?.binding
        }
    }

    override fun createBinding(parent: ViewGroup): ItemAnswerBinding {
        return ItemAnswerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onBind(
        binding: ItemAnswerBinding,
        item: QuizQuestionUIModel.Answer,
        position: Int
    ) {
        with(binding){
            optionTitleTextView.text = item.answer
            answerCardView.setTint(R.color.neutral_light_gray)
            correctPointTextView.visibility = View.GONE

            answerCardView.setOnClickListener {
                if (clickable) {
                    quizManager.handleAnswerClick(binding, item, position)
                }
            }
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

    fun setRecyclerView(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }
}