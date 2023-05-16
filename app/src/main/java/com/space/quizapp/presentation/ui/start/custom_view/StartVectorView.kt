package com.space.quizapp.presentation.ui.start.custom_view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.space.quizapp.R

/**
 * A custom view that draws a blue vector on the top and a white vector on the bottom.
 */
class StartVectorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val fillPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    private val path1 by lazy { Path() }
    private val path2 by lazy { Path() }
    private val rectF by lazy { RectF() }

    private val blueColor = ContextCompat.getColor(context, R.color.blue_secondary_default)
    private val whiteColor = ContextCompat.getColor(context, R.color.neutral_white)


    init {
        fillPaint.color = blueColor // Set the fill color for the first path
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val width = width.toFloat()
        val height = height.toFloat()
        val cornerRadius = height / 2f

        // Drawing the first path
        path1.apply {
            reset()
            moveTo(0f, 0f)
            lineTo(width, 0f)
            lineTo(width, height - cornerRadius)
            rectF.set(width - 2 * cornerRadius, height - 2 * cornerRadius, width, height)
            arcTo(rectF, 0f, 90f)
            lineTo(0f, height)
            close()
        }
        canvas.drawPath(path1, fillPaint)

        // Drawing the second path
        fillPaint.color = whiteColor // Set the fill color for the second path
        fillPaint.alpha = 10 // Set the fill alpha for the second path

        path2.apply {
            reset()
            moveTo(0f, height / 2f)
            cubicTo(0f, height / 4f, width / 3f, 0f, width, 0f)
            lineTo(width, height - cornerRadius)
            rectF.set(width - 4 * cornerRadius, height - 1 * cornerRadius, width, height * 2)
            arcTo(rectF, 0f, 90f)
            lineTo(0f, height / 2f)
            close()
        }
        canvas.drawPath(path2, fillPaint)
    }
}
