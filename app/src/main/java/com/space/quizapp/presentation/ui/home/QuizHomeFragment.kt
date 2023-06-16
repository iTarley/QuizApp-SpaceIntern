package com.space.quizapp.presentation.ui.home


import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizHomeBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.*
import kotlin.reflect.KClass

class QuizHomeFragment : QuizBaseFragment<QuizHomeViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz_home
    override val viewModelClass: KClass<QuizHomeViewModel>
        get() = QuizHomeViewModel::class

    private val binding by viewBinding(FragmentQuizHomeBinding::bind)

    override fun onBindViewModel() {
        navigate()
        showGpaScore()
    }

    private fun showGpaScore() {
        viewModel.updateSession()

        /**
         * Observe the session and load the user points
         */
        viewModel.session.observe(viewLifecycleOwner) { session ->
            session?.let {
                binding.helloUserTextView.text = buildString {
                    append(getString(R.string.hello_user))
                    append(it)
                }
                viewModel.loadUserPoints(it)
            }
        }

        /**
         * Observe the user points and display it
         */
        viewModel.userPoints.observe(viewLifecycleOwner) { userPoints ->
            val gpaScoreText = getString(R.string.gpa_score)
            val pointsText = userPoints.toString()

            binding.gpaTextView.setColoredTextWithPrefix(
                gpaScoreText,
                pointsText,
                ContextCompat.getColor(requireContext(), R.color.yellow_primary)
            )
        }
    }

    private fun navigate() {
        with(binding) {
            blueGpaVectorView.setOnClickListener {
                navigateSafe(findNavController(),QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizPointsFragment())
            }
            logOutButton.setOnClickListener {
                showLogOutDialog()
            }
        }
    }

    private fun showLogOutDialog() {
        showDialog(R.layout.dialog_listener, onPositiveButtonClick = {
            lifecycleScope {
                viewModel.clearUserSession(findNavController(),QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizLogInFragment())
            }
        })
    }
}