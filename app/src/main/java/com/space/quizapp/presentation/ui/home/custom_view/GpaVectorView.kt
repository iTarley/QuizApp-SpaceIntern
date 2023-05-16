package com.space.quizapp.presentation.ui.home.custom_view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.graphics.PathParser
import com.space.quizapp.R

/**
 * A custom view that draws a blue vector with with white arrow and light blue space for gpa.
 */
class GpaVectorView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val blueColor = ContextCompat.getColor(context, R.color.blue_secondary_default)
    private val whiteColor = ContextCompat.getColor(context, R.color.neutral_white)


    private val pathData = arrayOf(
        "M26,0L317,0A26,26 0,0 1,343 26L343,49A26,26 0,0 1,317 75L26,75A26,26 0,0 1,0 49L0,26A26,26 0,0 1,26 0z",
        "M309,37L301.5,43.06L301.5,30.94L309,37Z",
        "M32,20L86,20A14,14 0,0 1,100 34L100,41A14,14 0,0 1,86 55L32,55A14,14 0,0 1,18 41L18,34A14,14 0,0 1,32 20z"
    )

    private val fillColors = intArrayOf(blueColor, whiteColor, whiteColor)
    private val fillAlphas = floatArrayOf(1f, 1f, 0.14f)

    private val paints = Array(fillColors.size) { i ->
        Paint().apply {
            color = fillColors[i]
            style = Paint.Style.FILL
            isAntiAlias = true
            alpha = (fillAlphas[i] * 255).toInt()
        }
    }

    private val paths = Array(pathData.size) { i -> PathParser.createPathFromPathData(pathData[i]) }
    private val bounds by lazy { RectF() }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        calculateBounds()
        val scale = calculateScale()
        val matrix = Matrix().apply { reset(); postScale(scale, scale) }

        for (i in paths.indices) {
            val path = paths[i]
            val paint = paints[i]

            path.transform(matrix)
            canvas.drawPath(path, paint)
        }
    }

    private fun calculateBounds() {
        bounds.setEmpty()
        for (path in paths) {
            path.computeBounds(bounds, true)
        }
    }

    private fun calculateScale(): Float {
        val viewWidth = width.toFloat()
        val viewHeight = height.toFloat()
        val viewAspectRatio = viewWidth / viewHeight

        val vectorWidth = 343f // Width defined in the vector drawable
        val vectorHeight = 75f // Height defined in the vector drawable
        val vectorAspectRatio = vectorWidth / vectorHeight

        return if (viewAspectRatio > vectorAspectRatio) {
            viewHeight / vectorHeight
        } else {
            viewWidth / vectorWidth
        }
    }
}
