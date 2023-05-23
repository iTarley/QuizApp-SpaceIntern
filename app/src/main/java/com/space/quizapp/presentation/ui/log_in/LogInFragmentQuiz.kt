package com.space.quizapp.presentation.ui.log_in


import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentLoginBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class LogInFragmentQuiz : QuizBaseFragment<LogInViewModel>() {
    override val layout: Int
        get() = R.layout.fragment_login
    override val viewModelClass: KClass<LogInViewModel>
        get() = LogInViewModel::class

    private val binding by viewBinding(FragmentLoginBinding::bind)

    private lateinit var viewModel: LogInViewModel

    override fun onBindViewModel(viewModel: LogInViewModel) {
        navigate()
    }

    private fun navigate() {
        binding.logInButton.setOnClickListener {
            if (binding.inputUserNameEditText.text.toString().isNotEmpty()) {
                navigateSafe(LogInFragmentDirections.actionLoginFragmentToQuizHomeFragment())
            }
        }
    }
}