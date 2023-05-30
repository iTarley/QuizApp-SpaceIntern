package com.space.quizapp.presentation.ui.home.adapter


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.space.quizapp.R
import com.space.quizapp.databinding.ItemSubjectBinding
import com.space.quizapp.domain.model.QuizDomainModelItem
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.utils.DiffCallback
import com.space.quizapp.utils.extensions.viewBinding

class QuizListAdapter: ListAdapter<QuizUIModel, QuizListAdapter.QuizViewHolder>(DiffCallback()) {

    private var listener: OnClickListener<QuizUIModel>? = null

    fun setListener(listener: OnClickListener<QuizUIModel>) {
        this.listener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuizViewHolder {
        return QuizViewHolder(parent.viewBinding(ItemSubjectBinding::inflate))
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.onBind(getItem(position),listener)
    }

    class QuizViewHolder(private val binding: ItemSubjectBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(item:QuizUIModel,listener: OnClickListener<QuizUIModel>?){
            with(binding){
                subjectTextView.text = item.quizTitle
                descriptionTextView.text = item.quizDescription
                iconSubjectImageView.load(item.image)
                startImageButton.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.ic_next, 0)
                setListener(this.root){
                    listener?.onClick(item,adapterPosition)
                }
            }

        }

        private fun setListener(view: View, onItemClick: () -> Unit) {
            view.setOnClickListener {
                onItemClick()
            }
        }
    }
}