package com.space.quizapp.presentation.ui.log_in.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.space.quizapp.R
import com.space.quizapp.presentation.ui.base.custom_view.QuizBaseCustomView

/**
 * A custom view that draws a blue vector on the top and a white vector on the bottom.
 */
class QuizLogInVectorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : QuizBaseCustomView(context, attrs, defStyleAttr) {

    private val radius get() = width / 2
    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val blueColor = ContextCompat.getColor(context, R.color.blue_secondary_default)
    private val blueFadeColor = ContextCompat.getColor(context, R.color.blue_secondary_fade)

    /**
     * Draws a vector on the top.
     */
    private fun drawVector(canvas: Canvas) {
        val widthHeightDiff = (width / 2) - (height / 3) // Difference between the width and 1/3 of the height
        val heightThird = height / 3 + widthHeightDiff // 1/3 of the height + the difference

        path.apply {
            reset()
            paint.color = blueFadeColor
            // Add a circle at the specified position and direction.
            addCircle(radius, heightThird, radius, Path.Direction.CW)
            // Add another circle at a different position and direction.
            addCircle(radius, height - radius, radius, Path.Direction.CW)
            // Move the starting point of the path to the top left corner.
            moveTo(0f, 0f)
            // Create a line segment from the current point to the top right corner.
            lineTo(width, 0f)
            // Create a line segment from the top right corner to a point above the bottom right corner.
            lineTo(width, height - radius)
            // Create a line segment from the previous point to a point on the bottom left curve of the added circle.
            lineTo(radius, height)
            // Create a line segment from the previous point to the bottom left corner.
            lineTo(0f, height)
            // Create a line segment from the previous point to a point on the top left curve of the added circle.
            lineTo(0f, heightThird)
            // Create a line segment from the previous point to the starting point of the path.
            lineTo(radius, 0f)
            close()
            canvas.drawPath(path, paint)
        }
    }

    /**
     * Draws a corner for the top vector.
     */
    private fun drawCorner(canvas: Canvas) {
        path.apply {
            reset()
            paint.color = blueColor
            // Move the starting point of the path to the top left corner.
            moveTo(0f, 0f)
            // Create a line segment from the current point to the top right corner.
            lineTo(radius, 0f)
            // Create a line segment from the previous point to the bottom left corner.
            lineTo(0f, height / 2)
            close()
            canvas.drawPath(path, paint)
        }
    }

    override fun startDrawing(canvas: Canvas) {
        drawCorner(canvas)
        drawVector(canvas)
    }
}
