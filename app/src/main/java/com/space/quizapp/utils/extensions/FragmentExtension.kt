package com.space.quizapp.utils.extensions

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.space.quizapp.R

//TODO switch findViewById to binding
fun Fragment.showDialog(layoutResId: Int, onPositiveButtonClick: () -> Unit) {
    val dialog = Dialog(requireContext())
    dialog.setContentView(layoutResId)

    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)

    val yesButton = dialog.findViewById<Button>(R.id.yesButton)
    val noButton = dialog.findViewById<Button>(R.id.noButton)

    yesButton.setOnClickListener {
        dialog.dismiss()
        onPositiveButtonClick.invoke()
    }

    noButton.setOnClickListener {
        dialog.dismiss()
    }

    dialog.show()
}