package com.space.quizapp.presentation.ui.quiz.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.presentation.ui.base.adapter.OnClickListener
import com.space.quizapp.utils.extensions.viewBinding

class QuizQuestionsAdapter:BaseListAdapter<String,ItemAnswerBinding>(MyItemDiffCallback()) {

    private var listener: OnClickListener<String>? = null

    fun setListener(listener: OnClickListener<String>) {
        this.listener = listener
    }

    override fun createBinding(parent: ViewGroup): ItemAnswerBinding {
        return parent.viewBinding(ItemAnswerBinding::inflate)
    }

    override fun onBind(binding: ItemAnswerBinding, item: String,position:Int) {
        binding.optionTitleTextView.text = item
        setListener(binding.root) {
            listener?.onClick(item, position)
        }
    }

    private fun setListener(view: View, onItemClick: () -> Unit) {
        view.setOnClickListener {
            onItemClick()
        }
    }

    private class MyItemDiffCallback : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            // Compare based on unique identifiers of the items
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            // Compare based on the actual content of the items
            return oldItem == newItem
        }
    }
}