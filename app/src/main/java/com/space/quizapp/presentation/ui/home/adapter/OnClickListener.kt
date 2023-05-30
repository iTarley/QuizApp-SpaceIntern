package com.space.quizapp.presentation.ui.home.adapter

interface OnClickListener<T : Any> {
    fun onClick(item: T, position: Int)
}