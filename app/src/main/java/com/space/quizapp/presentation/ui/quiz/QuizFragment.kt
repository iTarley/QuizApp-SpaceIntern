package com.space.quizapp.presentation.ui.quiz


import android.util.Log
import androidx.navigation.fragment.navArgs
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizBinding
import com.space.quizapp.presentation.model.QuizUIModel
import com.space.quizapp.presentation.ui.base.adapter.OnClickListener
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.quiz.adapter.QuizQuestionsAdapter
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class QuizFragment : QuizBaseFragment<QuizViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz
    override val viewModelClass: KClass<QuizViewModel>
        get() = QuizViewModel::class

    private val binding by viewBinding(FragmentQuizBinding::bind)
    private val args: QuizFragmentArgs by navArgs()
    var index = 0


    private val adapter by lazy {
        QuizQuestionsAdapter()
    }

    override fun onBindViewModel() {
        observe()
    }

    private fun setAdapter(){
        binding.quizRecyclerView.adapter = adapter
    }

    /**
     * Function to observe the quiz data
     */
    private fun observe() {
        val position = args.position
        viewModel.fetchQuizData()
        viewModel.quizData.observe(viewLifecycleOwner) { quiz ->
            with(binding) {
                updateQuiz(quiz, position)
                navTitleTextView.text = quiz[position].quizTitle
            }
        }
        setAdapter()
    }

    /**
     * function to update the quiz
     */
    //TODO 1. try to make it with flow
    private fun updateQuiz(quiz: List<QuizUIModel>, args: Int) {
        var question = quiz[args].questions[index]
        with(binding) {
            questionTextView.text = question.questionTitle
            adapter.submitList(question.answers)
            adapter.setListener(object: OnClickListener<String>{
                override fun onClick(item: String, position: Int) {
                    question = quiz[args].questions[index]
                    if (item == question.correctAnswer) {
                        index++
                        if (index < quiz[args].questions.size) {
                            val nextQuestion = quiz[args].questions[index]
                            questionTextView.text = nextQuestion.questionTitle
                            adapter.submitList(nextQuestion.answers)
                        }
                    }
                }
            })
        }
    }
}