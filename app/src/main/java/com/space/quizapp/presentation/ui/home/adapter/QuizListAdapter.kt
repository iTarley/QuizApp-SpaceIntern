package com.space.quizapp.presentation.ui.home.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import coil.load
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemSubjectBinding
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.presentation.ui.base.adapter.BaseDiffUtil
import com.space.quizapp.presentation.ui.base.adapter.BaseListAdapter
import com.space.quizapp.presentation.ui.base.adapter.OnClickListener
import com.space.quizapp.utils.extensions.viewBinding

class QuizListAdapter: BaseListAdapter<QuizUIModel, ItemSubjectBinding>(MyItemDiffCallback()) {

    override fun createBinding(parent: ViewGroup): ItemSubjectBinding {
        return parent.viewBinding(ItemSubjectBinding::inflate)
    }

    override fun onBind(
        binding: ItemSubjectBinding,
        item: QuizUIModel,
        onClickCallback: ((QuizUIModel) -> Unit)?
    ) {
        with(binding){
            subjectTextView.text = item.quizTitle
            descriptionTextView.text = item.quizDescription
            iconSubjectImageView.load(item.image)
            startImageButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0)
            setListener(binding.root) {
                onClickCallback?.invoke(item)
            }
        }
    }

    private fun setListener(view: View, onItemClick: () -> Unit) {
        view.setOnClickListener {
            onItemClick()
        }
    }

    private class MyItemDiffCallback : DiffUtil.ItemCallback<QuizUIModel>() {

        override fun areItemsTheSame(oldItem: QuizUIModel, newItem: QuizUIModel): Boolean {
            // Compare based on unique identifiers of the items
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuizUIModel, newItem: QuizUIModel): Boolean {
            // Compare based on the actual content of the items
            return oldItem == newItem
        }
    }
}

