package com.space.quizapp.presentation.ui.points

import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizPointBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.*
import kotlin.reflect.KClass

class QuizPointsFragment : QuizBaseFragment<QuizPointsViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz_point
    override val viewModelClass: KClass<QuizPointsViewModel>
        get() = QuizPointsViewModel::class

    private val binding by viewBinding(FragmentQuizPointBinding::bind)

    override fun onBind() {
        navigate()
    }

    private fun navigate() {
        with(binding){
            navBackImageButton.setOnClickListener {
                popBackStack(it)
            }
            logOutButton.setOnClickListener {
                showLogOutDialog()
            }
        }
    }

    private fun showLogOutDialog() {
        showDialog(R.layout.dialog_listener,getString(R.string.leaving_question), onPositiveButtonClick = {
            lifecycleScope {
                viewModel.clearUserSession()
            }
        })
    }
}