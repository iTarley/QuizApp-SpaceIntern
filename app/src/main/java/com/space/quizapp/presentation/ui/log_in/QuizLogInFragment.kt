package com.space.quizapp.presentation.ui.log_in


import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentLoginBinding
import com.space.quizapp.presentation.model.UserUIModel
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.Resource
import com.space.quizapp.utils.extensions.lifecycleScope
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class QuizLogInFragment : QuizBaseFragment<QuizLogInViewModel>() {
    override val layout: Int
        get() = R.layout.fragment_login
    override val viewModelClass: KClass<QuizLogInViewModel>
        get() = QuizLogInViewModel::class

    private val binding by viewBinding(FragmentLoginBinding::bind)

    override fun onBindViewModel() {
        authorizeUser()
        observeSession()
    }

    /**
     * Observe the session, if session is not empty navigate to home fragment
     */
    private fun observeSession() {
        viewModel.observeSession()
        lifecycleScope {
            viewModel.session.observe(this@QuizLogInFragment) {
                if (it?.isNotEmpty() == true) {
                    navigateSafe(QuizLogInFragmentDirections.actionQuizLogInFragmentToQuizHomeFragment())
                }
            }
        }
    }

    /**
     * Authorize the user
     */
    private fun authorizeUser() {
        with(binding) {
            logInButton.setOnClickListener {
                val username = inputUserNameEditText.text.toString()
                if (username.isNotEmpty()) {
                    viewModel.authorizeUser(UserUIModel(username = username))
                }
                observeStatus()
            }
        }
    }

    /**
     * Observe the registration status
     */
    private fun observeStatus() {
        viewModel.registrationStatus.observe(this) { status ->
            when (status) {
                is Resource.Success -> {
                    // Handle registration success
                    navigateSafe(QuizLogInFragmentDirections.actionQuizLogInFragmentToQuizHomeFragment())
                }
                is Resource.Error -> {
                    // Handle registration error
                    binding.inputLayout.error = status.message
                }
            }
        }
    }
}