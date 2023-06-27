package com.space.quizapp.presentation.ui.quiz


import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.quiz.adapter.QuizQuestionsAdapter
import com.space.quizapp.utils.extensions.*
import kotlin.reflect.KClass

class QuizFragment : QuizBaseFragment<QuizViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz
    override val viewModelClass: KClass<QuizViewModel>
        get() = QuizViewModel::class

    private val binding by viewBinding(FragmentQuizBinding::bind)
    private val args: QuizFragmentArgs by navArgs()
    private val adapter by lazy {
        QuizQuestionsAdapter()
    }

    override fun onBind() {
        setObserver()
        setBackListener()
        setLogOutButton()
    }

    private fun setAdapter() {
        binding.quizRecyclerView.adapter = adapter
        adapter.setRecyclerView(binding.quizRecyclerView)
    }

    private fun setBackListener() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (adapter.points == 0.0) {
                    popBackStack(requireView())
                } else {
                    showDialog()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }


    private fun saveGpa(index: Int, quizPoints: Double) {
        observe(viewModel.quizData) {
            viewModel.saveGpa(it[index].subjectId, quizPoints)
        }
    }

    /**
     * Function to observe the quiz data
     */
    private fun setObserver() {
        val position = args.position
        val questionsCount = args.quizCount
        val quizTitle = args.quizTitle
        setAdapter()
        viewModel.fetchQuizData(position)
        binding.navTitleTextView.text = quizTitle
        /**
         * Observe the quiz data, set the adapter and submit the list
         */
        observe(viewModel.quizData) { answer ->
            adapter.submitList(answer[adapter.quizId].data)
            val currentQuestion = answer[adapter.quizId]
            /**
             * Set the progress bar and text
             */
            with(binding) {
                progressTextView.text =
                    getString(R.string.progress_text, adapter.quizId.inc(), questionsCount)
                currentPointTextView.text =
                    String.format(getString(R.string.current_point), adapter.points)
                progressBar.max = questionsCount
                progressBar.progress = adapter.quizId.inc()
                questionTextView.text = currentQuestion.questionTitle
            }
            /**
             * Set new question and answers
             */
            binding.startQuizButton.setOnClickListener {
                adapter.submitList(answer[adapter.quizId].data)
                adapter.clickable = true
                binding.progressTextView.text =
                    getString(R.string.progress_text, adapter.quizId.inc(), questionsCount)
                binding.currentPointTextView.text =
                    String.format(getString(R.string.current_point), adapter.points)
                binding.progressBar.progress = adapter.quizId.inc()
                if (adapter.quizId.inc() == questionsCount) {
                    binding.startQuizButton.text = getString(R.string.finish)
                }
                if (adapter.lastQuestion) {
                    saveGpa(adapter.quizId, adapter.points)
                    val message = String.format(getString(R.string.you_have), adapter.points)
                    showDialog(
                        R.layout.dialog_alert,
                        message,
                        cancelable = false,
                        onPositiveButtonClick = {
                            popBackStack(requireView())
                        }
                    )
                }
            }
        }
    }

    private fun showDialog() {
        showDialog(
            R.layout.dialog_listener,
            getString(R.string.leaving_question),
            onPositiveButtonClick = {
                saveGpa(index = adapter.quizId, quizPoints = adapter.points)
                val message =
                    String.format(getString(R.string.you_have), adapter.points)
                showDialog(
                    R.layout.dialog_alert,
                    message,
                    cancelable = false,
                    onPositiveButtonClick = {
                        popBackStack(requireView())
                    }
                )
            }
        )
    }

    private fun setLogOutButton() {
        binding.exitImageButton.setOnClickListener {
            if (adapter.points == 0.0) {
                popBackStack(requireView())
            } else {
                showDialog()
            }
        }
    }
}