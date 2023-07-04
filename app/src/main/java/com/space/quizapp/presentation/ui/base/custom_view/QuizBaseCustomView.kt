package com.space.quizapp.presentation.ui.base.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

abstract class QuizBaseCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    protected val path by lazy { Path() }
    protected val width get() = getWidth().toFloat()
    protected val height get() = getHeight().toFloat()

    abstract fun startDrawing(canvas: Canvas)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        startDrawing(canvas)
    }
}