package com.space.quizapp.presentation.ui.home

import androidx.core.content.ContextCompat
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizHomeBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.home.adapter.QuizListAdapter
import com.space.quizapp.utils.extensions.*
import kotlin.reflect.KClass

class QuizHomeFragment : QuizBaseFragment<QuizHomeViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz_home
    override val viewModelClass: KClass<QuizHomeViewModel>
        get() = QuizHomeViewModel::class

    private val binding by viewBinding(FragmentQuizHomeBinding::bind)

    private val adapter by lazy {
        QuizListAdapter()
    }

    override fun onBind() {
        navigate()
        showGpaScore()
        observe()
        setOnClickListener()
    }

    private fun showGpaScore() {
        viewModel.updateSession()

        /**
         * Observe the session and load the user points
         */
        observe(viewModel.session) { session ->
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
        observe(viewModel.userPoints) { userPoints ->
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
        with(binding){
            blueGpaVectorView.setOnClickListener {
                viewModel.navigate(QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizPointsFragment())
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

    private fun observe() {
        binding.startQuizRecyclerView.adapter = adapter
        observe(viewModel.quizData) {
            adapter.submitList(it)
        }
        viewModel.fetchQuizData()
    }

    private fun setOnClickListener() {
        adapter.onItemClickListener {
            viewModel.navigate(
                QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizFragment(
                    it.quizTitle,
                    it.id
                )
            )
        }
    }
}