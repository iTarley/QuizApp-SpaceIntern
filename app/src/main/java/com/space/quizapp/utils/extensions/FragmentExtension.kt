package com.space.quizapp.utils.extensions

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.space.quizapp.R
import com.space.quizapp.databinding.DialogAlertBinding

fun Fragment.navigateSafe(action: NavDirections) {
    findNavController().currentDestination?.getAction(action.actionId)?.let {
        findNavController().navigate(action)
    }
}
fun popBackStack(it: View){
    Navigation.findNavController(it).popBackStack()
}


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