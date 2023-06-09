package com.space.quizapp.presentation.ui.quiz


import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizBinding
import com.space.quizapp.presentation.model.QuizQuestionUIModel
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.presentation.ui.quiz.adapter.QuizQuestionsAdapter
import com.space.quizapp.utils.extensions.observe
import com.space.quizapp.utils.extensions.viewBinding
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
        observe()
    }

    private fun setAdapter() {
        binding.quizRecyclerView.adapter = adapter
    }

    /**
     * Function to observe the quiz data
     */
    private fun observe() {
        val position = args.position
        val quizTitle = args.quizTitle
        setAdapter()

        viewModel.fetchQuizData(position)
        binding.navTitleTextView.text = quizTitle

        observe(viewModel.quizAnswer) { answer ->
            adapter.submitList(answer)

            val quiz = viewModel.quizData.value
            val itemId = viewModel.itemId

            if (quiz != null) {
                binding.questionTextView.text = quiz[itemId].questionTitle
                validateAnswer(quiz[itemId])
            }
        }

        observe(viewModel.correctStatus) {
            Snackbar.make(binding.root, getString(R.string.corrent_answer), Snackbar.LENGTH_SHORT).show()
        }

        observe(viewModel.wrongStatus) {
            Snackbar.make(binding.root, getString(R.string.wrong_answer), Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun validateAnswer(quiz:QuizQuestionUIModel){
        adapter.onItemClickListener{
            viewModel.validateAnswer(it,quiz)
        }
    }
}