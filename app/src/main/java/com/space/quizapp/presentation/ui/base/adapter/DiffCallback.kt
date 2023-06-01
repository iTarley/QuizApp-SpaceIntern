package com.space.quizapp.presentation.ui.base.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class DiffCallback<M : Any> : DiffUtil.ItemCallback<M>() {

    override fun areItemsTheSame(oldItem: M, newItem: M): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: M, newItem: M): Boolean {
        return oldItem == newItem
    }
}