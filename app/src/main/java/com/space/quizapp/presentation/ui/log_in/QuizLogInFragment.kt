package com.space.quizapp.presentation.ui.log_in


import androidx.navigation.fragment.findNavController
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentLoginBinding
import com.space.quizapp.presentation.model.UserUIModel
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.lifecycleScope
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

    // NAVIGATION FIXED IN NEXT PR
    private fun observeSession() {
        viewModel.observeSession()
        lifecycleScope {
            viewModel.session.observe(this@QuizLogInFragment) {
                if (it?.isNotEmpty() == true) {
                    viewModel.navigateToHome(findNavController())
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
                    viewModel.authorizeUser(UserUIModel(username = username),findNavController())
                }
                observeStatus()
            }
        }
    }

    /**
     * Observe the error status
     */
    private fun observeStatus() {
        viewModel.errorStatus.observe(this){
            binding.inputLayout.error = getString(it)
        }
    }
}