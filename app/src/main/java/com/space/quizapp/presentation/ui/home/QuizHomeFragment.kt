package com.space.quizapp.presentation.ui.home


import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizHomeBinding
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.base.adapter.OnClickListener
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

    override fun onBindViewModel() {
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
        binding.blueGpaVectorView.setOnClickListener {
            viewModel.navigateTo(
                findNavController(),
                QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizPointsFragment()
            )
        }
        binding.logOutButton.setOnClickListener {
            showLogOutDialog()
        }
    }

    private fun showLogOutDialog() {
        showDialog(R.layout.dialog_listener, onPositiveButtonClick = {
            lifecycleScope {
                viewModel.clearUserSession(
                    findNavController(),
                    QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizLogInFragment()
                )
            }
        })
    }

    private fun observe() {
        binding.startQuizRecyclerView.adapter = adapter
        viewModel.quizData.observe(viewLifecycleOwner) { quiz ->
            adapter.submitList(quiz)
        }
        viewModel.fetchQuizData()
    }

    private fun setOnClickListener() {
        adapter.setListener(object: OnClickListener<QuizUIModel> {
            override fun onClick(item: QuizUIModel, position: Int) {
                viewModel.navigateTo(findNavController(),QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizFragment(position))
            }
        })
    }
}