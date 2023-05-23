package com.space.quizapp.presentation.ui.quiz


import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class QuizFragmentQuiz : QuizBaseFragment<QuizViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz
    override val viewModelClass: KClass<QuizViewModel>
        get() = QuizViewModel::class

    private val binding by viewBinding(FragmentQuizBinding::bind)

    private lateinit var viewModel: QuizViewModel

    override fun onBindViewModel(viewModel: QuizViewModel) {

    }

}