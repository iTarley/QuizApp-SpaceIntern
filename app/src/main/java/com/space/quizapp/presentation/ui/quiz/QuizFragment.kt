package com.space.quizapp.presentation.ui.quiz


import androidx.activity.addCallback
import androidx.navigation.fragment.navArgs
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.quiz.adapter.QuizQuestionsAdapter
import com.space.quizapp.presentation.ui.quiz.adapter.QuizState
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
    }

    private fun setAdapter() {
        binding.quizRecyclerView.adapter = adapter
    }

    private fun setBackListener() {
        requireActivity().onBackPressedDispatcher.addCallback {
            showDialog(
                R.layout.dialog_listener,
                getString(R.string.leaving_question),
                onPositiveButtonClick = {
                    saveGpa(index = adapter.quizId, quizPoints = adapter.quizId)
                    showDialog(
                        R.layout.dialog_alert,
                        buildString {
                            append(getString(R.string.you_have))
                            append(adapter.quizId)
                            append(getString(R.string.point))
                        },
                        cancelable = false,
                        onPositiveButtonClick = {
                            popBackStack(requireView())
                        })
                })
        }
    }

    private fun saveGpa(index:Int,quizPoints:Int){
        observe(viewModel.quizData){
            viewModel.saveGpa(it[index].subjectId, quizPoints)
        }
    }

    /**
     * Function to observe the quiz data
     */
    private fun setObserver() {
        val position = args.position
        val quizTitle = args.quizTitle
        setAdapter()
        viewModel.fetchQuizData(position)
        binding.navTitleTextView.text = quizTitle

        /**
         * Observe the quiz data, set the adapter and submit the list
         */
        observe(viewModel.quizData) { answer ->
            adapter.setQuiz(answer)
            adapter.submitList(answer[adapter.quizId].data)
            binding.questionTextView.text = answer[adapter.quizId].questionTitle

            binding.startQuizButton.setOnClickListener {
                adapter.submitList(answer[adapter.quizId].data)

                if (adapter.quizState == QuizState.FINISHED) {
                    saveGpa(index = adapter.quizId,adapter.quizId + 1)
                    showDialog(
                        R.layout.dialog_alert,
                        buildString {
                            append(getString(R.string.you_have))
                            append(adapter.quizId + 1)
                            append(getString(R.string.point))
                        },
                        cancelable = false,
                        onPositiveButtonClick = {
                            popBackStack(requireView())
                        })
                }
            }
        }
    }
}