package com.space.quiz.presentation.ui


import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.navArgs
import com.space.quiz.R
import com.space.quiz.databinding.FragmentQuizBinding
import com.space.quiz.presentation.model.QuizQuestionUIModel
import com.space.util.base.fragment.QuizBaseFragment
import com.space.quiz.presentation.ui.adapter.QuizQuestionsAdapter
import com.space.quizapi.QuizFeatureNavigator
import com.space.util.extensions.observe
import com.space.util.extensions.popBackStack
import com.space.util.extensions.showDialog
import com.space.util.extensions.viewBinding
import org.koin.android.ext.android.inject
import org.koin.java.KoinJavaComponent.inject
import kotlin.reflect.KClass

class QuizFragment : QuizBaseFragment<QuizViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz
    override val viewModelClass: KClass<QuizViewModel>
        get() = QuizViewModel::class

    private val quizFragmentNavigator by inject<QuizFeatureNavigator>()

    private val binding by viewBinding(FragmentQuizBinding::bind)
    private val args = arguments

    private val adapter by lazy {
        QuizQuestionsAdapter { isCorrectAnswer, points, isLastQuestion ->
            viewModel.updatePoints(isCorrectAnswer, points)
            binding.currentPointTextView.text =
                String.format(getString(R.string.current_point), viewModel.points)
            if (isLastQuestion) {
                viewModel.markAsLastQuestion()
            } else {
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

//    private fun saveGpa(index: Int, quizPoints: Double) {
//        observe(viewModel.quizData) {
//            viewModel.saveGpa(it[index].subjectId, quizPoints)
//        }
//    }

    /**
     * Function to observe the quiz data
     */
    private fun setObserver() {
        val position = args?.getInt("position", 0) ?: 1
        val questionsCount = args?.getInt("quizCount", 5) ?: 5
        val quizTitle = args?.getString("quizTitle") ?: "geography"
        setAdapter()
        viewModel.fetchQuizData(position)
        binding.navTitleTextView.text = quizTitle
        /**
         * Observe the quiz data, set the adapter and submit the list
         */
        observe(viewModel.quizData) { answer ->
            setFirstQuestion(answer, questionsCount)

            //Set new question and answers
            binding.startQuizButton.setOnClickListener {
                setNextQuestion(answer, questionsCount)
            }
        }
    }

    private fun showDialog() {
        showDialog(
            R.layout.dialog_listener,
            getString(R.string.leaving_question),
            onPositiveButtonClick = {
                showAlertDialog(R.layout.dialog_alert)
            }
        )
    }

    private fun setFirstQuestion(answer: List<QuizQuestionUIModel>, questionsCount: Int) {
        adapter.submitList(answer[viewModel.quizId].data)
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
            questionTextView.text = answer[viewModel.quizId].questionTitle
        }
    }

    private fun setNextQuestion(answer: List<QuizQuestionUIModel>, questionsCount: Int) {
        adapter.submitList(answer[viewModel.quizId].data)
        adapter.clickable = true

        with(binding) {
            questionTextView.text = answer[viewModel.quizId].questionTitle
            progressTextView.text =
                getString(R.string.progress_text, viewModel.quizId.inc(), questionsCount)
            progressBar.progress = viewModel.quizId.inc()
        }
        if (viewModel.quizId.inc() == questionsCount) {
            binding.startQuizButton.text = getString(R.string.finish)
        }
        if (viewModel.lastQuestion) {
            if (viewModel.points == 0){
                showAlertDialog(R.layout.dialog_alert_sad)
            }else{
                showAlertDialog(R.layout.dialog_alert)
            }
        }
    }

    private fun setLogOutButton() {
        binding.exitImageButton.setOnClickListener {
            checkPoints()
        }
    }

    private fun checkPoints() {
        if (viewModel.points == 0) {
            popBackStack(requireView())
        } else {
            showDialog()
        }
    }

    private fun showAlertDialog(layout:Int){
//        saveGpa(viewModel.quizId, viewModel.points.toDouble())
        val message = String.format(getString(R.string.you_have), viewModel.points)
        showDialog(
            layout,
            message,
            cancelable = false,
            onPositiveButtonClick = {
                popBackStack(requireView())
            }
        )
    }
}