package com.space.quizapp.presentation.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import coil.load
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemSubjectBinding
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.presentation.ui.base.adapter.OnClickListener
import com.space.quizapp.utils.extensions.viewBinding

class QuizListAdapter: BaseListAdapter<QuizUIModel, ItemSubjectBinding>() {

    private var listener: OnClickListener<QuizUIModel>? = null

    fun setListener(listener: OnClickListener<QuizUIModel>) {
        this.listener = listener
    }

    override fun createBinding(parent: ViewGroup): ItemSubjectBinding {
        return parent.viewBinding(ItemSubjectBinding::inflate)
    }

    override fun onBind(binding: ItemSubjectBinding, item: QuizUIModel,position:Int) {
        with(binding){
            subjectTextView.text = item.quizTitle
            descriptionTextView.text = item.quizDescription
            iconSubjectImageView.load(item.image)
            startImageButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0)
            setListener(root) {
                listener?.onClick(item, position)
            }
        }
    }

    private fun setListener(view: View, onItemClick: () -> Unit) {
        view.setOnClickListener {
            onItemClick()
        }
    }
}