package com.space.quizapp.presentation.ui.quiz.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.space.quizapp.R
import com.space.quizapp.presentation.ui.base.custom_view.QuizBaseCustomView

/**
 * A custom view that draws a blue vector on the top.
 */
class QuizQuestionTopBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    QuizBaseCustomView(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.blue_secondary_light)
    }

    /**
     * Draws a vector on the top.
     */
    private fun drawVector(canvas: Canvas) {
        // Reset the path to ensure a clean starting point.
        path.reset()

        // Move the starting point of the path to the top left corner.
        path.moveTo(0f, 0f)

        // Draw a line from the current position to the top right corner of the canvas.
        path.lineTo(canvas.width.toFloat(), 0f)

        // Draw a line from the top right corner to the middle of the right side of the canvas.
        path.lineTo(canvas.width.toFloat(), canvas.height * 0.5f)

        // Draw a cubic Bézier curve from the middle of the right side to the bottom right corner of the canvas.
        // The control points are calculated based on specific ratios of the canvas width and height.
        path.cubicTo(
            canvas.width.toFloat(), canvas.height * 0.8f,
            canvas.width * 0.9f, canvas.height.toFloat(),
            canvas.width * 0.7f, canvas.height.toFloat()
        )

        // Draw a line from the bottom right corner to the bottom left corner of the canvas.
        path.lineTo(canvas.width * 0.3f, canvas.height.toFloat())

        // Draw a cubic Bézier curve from the bottom left corner to the top left corner of the canvas.
        // The control points are calculated based on specific ratios of the canvas width and height.
        path.cubicTo(
            canvas.width * 0.1f, canvas.height.toFloat(),
            0f, canvas.height * 0.8f,
            0f, canvas.height * 0.6f
        )

        // Close the path by drawing a line from the current position to the starting point (top left corner).
        path.close()

        // Draw the path on the canvas using the specified paint.
        canvas.drawPath(path, paint)
    }

    override fun startDrawing(canvas: Canvas) {
        drawVector(canvas)
    }
}
