package com.space.quizapp.presentation.ui.base.adapter

import androidx.recyclerview.widget.DiffUtil
//TODO GADAAKETE

abstract class BaseDiffUtil<T>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return areItemsSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return areContentsSame(oldItem, newItem)
    }

    protected abstract fun areItemsSame(oldItem: T, newItem: T): Boolean

    protected abstract fun areContentsSame(oldItem: T, newItem: T): Boolean
}



