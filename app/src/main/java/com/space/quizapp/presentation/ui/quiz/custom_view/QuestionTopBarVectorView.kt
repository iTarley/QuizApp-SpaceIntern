package com.space.quizapp.presentation.ui.quiz.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.space.quizapp.R

/**
 * A custom view that draws a blue vector on the top.
 */
class QuestionTopBarVectorView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val path by lazy { Path() }
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.blue_secondary_light)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()

        path.reset()
        path.moveTo(0f, 0f)
        path.lineTo(width, 0f)
        path.lineTo(width, height * 0.56f)
        path.cubicTo(
            width, height * 0.82f,
            width * 0.88f, height,
            width * 0.73f, height
        )
        path.lineTo(width * 0.27f, height)
        path.cubicTo(
            width * 0.12f, height,
            0f, height * 0.82f,
            0f, height * 0.56f
        )
        path.close()

        canvas.drawPath(path, paint)
    }
}
