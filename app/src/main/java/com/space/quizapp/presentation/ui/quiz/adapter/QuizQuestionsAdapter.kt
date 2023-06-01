package com.space.quizapp.presentation.ui.quiz.adapter

import android.view.View
import android.view.ViewGroup
import com.space.quizapp.databinding.ItemAnswerBinding
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.presentation.ui.base.adapter.OnClickListener
import com.space.quizapp.utils.extensions.viewBinding

class QuizQuestionsAdapter:BaseListAdapter<String,ItemAnswerBinding>() {

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
}