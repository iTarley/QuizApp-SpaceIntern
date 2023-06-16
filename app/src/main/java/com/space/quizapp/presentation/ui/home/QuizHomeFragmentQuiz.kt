package com.space.quizapp.presentation.ui.home


import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizHomeBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.showDialog
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class QuizHomeFragmentQuiz : QuizBaseFragment<QuizHomeViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz_home
    override val viewModelClass: KClass<QuizHomeViewModel>
        get() = QuizHomeViewModel::class

    private val binding by viewBinding(FragmentQuizHomeBinding::bind)

    private lateinit var viewModel: QuizHomeViewModel

    override fun onBindViewModel(viewModel: QuizHomeViewModel) {
        navigate()
    }

    private fun navigate() {

        with(binding){
            blueGpaVectorView.setOnClickListener {
                navigateSafe(QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizPointFragment())
            }
            logOutButton.setOnClickListener {
                showLogOutDialog()
            }
        }
    }

    private fun showLogOutDialog() {
        showDialog(
            R.layout.dialog_listener,
            onPositiveButtonClick = {
                navigateSafe(QuizHomeFragmentDirections.actionQuizHomeFragmentToLoginFragment())
            }
        )
    }
}