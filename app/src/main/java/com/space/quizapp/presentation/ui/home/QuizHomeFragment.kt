package com.space.quizapp.presentation.ui.home


import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentQuizHomeBinding
import com.space.quizapp.presentation.ui.base.fragment.QuizBaseFragment
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.showDialog
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class QuizHomeFragment : QuizBaseFragment<QuizHomeViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_quiz_home
    override val viewModelClass: KClass<QuizHomeViewModel>
        get() = QuizHomeViewModel::class

    private val binding by viewBinding(FragmentQuizHomeBinding::bind)

    override fun onBindViewModel() {
        navigate()
    }

    private fun showGpaScore(){
        //TODO - GET CURRENT AUTH USERNAME AND PASS IT TO THE FUNCTION (DATASTORE STATE HANDLER)
        viewModel.loadUserPoints("lukaadmin")
        viewModel.userPoints.observe(this){
            binding.gpaTextView.text = "GPA - ${it.toString()}"
        }
    }
    private fun navigate() {
        with(binding){
            blueGpaVectorView.setOnClickListener {
                navigateSafe(QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizPointFragment())
            }
            logOutButton.setOnClickListener {
                showLogOutDialog()
            }
    }

    private fun showLogOutDialog() {
        showDialog(
            R.layout.dialog_listener,
            onPositiveButtonClick = {
                navigateSafe(QuizHomeFragmentDirections.actionQuizHomeFragmentToQuizLogInFragment())
            }
        )
    }
}