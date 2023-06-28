package com.space.quizapp.presentation.ui.quiz


import android.graphics.Point
import android.util.Log
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
        QuizQuestionsAdapter { isCorrectAnswer, points,isLastQuestion ->
            viewModel.updatePoints(isCorrectAnswer, points)
            if (isLastQuestion){
                viewModel.markAsLastQuestion()
            }else{
                viewModel.incrementQuizId()
            }

        }
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
                checkPoints()
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
            adapter.submitList(answer[viewModel.quizId].data)
            val currentQuestion = answer[viewModel.quizId]
            /**
             * Set the progress bar and text
             */
            with(binding) {
                progressTextView.text =
                    getString(R.string.progress_text, viewModel.quizId.inc(), questionsCount)
                currentPointTextView.text =
                    String.format(getString(R.string.current_point), viewModel.points)
                progressBar.max = questionsCount
                progressBar.progress = viewModel.quizId.inc()
                questionTextView.text = currentQuestion.questionTitle
            }
            /**
             * Set new question and answers
             */
            binding.startQuizButton.setOnClickListener {
                Log.d("luka", "${viewModel.quizId}")

                adapter.submitList(answer[viewModel.quizId].data)
                adapter.clickable = true
                binding.progressTextView.text =
                    getString(R.string.progress_text, viewModel.quizId.inc(), questionsCount)
                binding.currentPointTextView.text =
                    String.format(getString(R.string.current_point), viewModel.points)
                binding.progressBar.progress = viewModel.quizId.inc()
                if (viewModel.quizId.inc() == questionsCount) {
                    binding.startQuizButton.text = getString(R.string.finish)
                }
                if (viewModel.lastQuestion) {
                    saveGpa(viewModel.quizId, viewModel.points)
                    val message = String.format(getString(R.string.you_have), viewModel.points)
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
                saveGpa(index = viewModel.quizId, quizPoints = viewModel.points)
                val message =
                    String.format(getString(R.string.you_have), viewModel.points)
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
            checkPoints()
        }
    }

    private fun checkPoints(){
        if (viewModel.points == 0.0) {
            popBackStack(requireView())
        } else {
            showDialog()
        }
    }
}