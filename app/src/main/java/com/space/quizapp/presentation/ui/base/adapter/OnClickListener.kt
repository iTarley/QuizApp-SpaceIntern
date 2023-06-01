package com.space.quizapp.presentation.ui.base.adapter

interface OnClickListener<T : Any> {
    fun onClick(item: T, position: Int)
}