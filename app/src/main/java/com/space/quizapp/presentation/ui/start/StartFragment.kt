package com.space.quizapp.presentation.ui.start


import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentStartBinding
import com.space.quizapp.presentation.ui.base.BaseFragment
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class StartFragment : BaseFragment<StartViewModel>() {


    override val layout: Int
        get() = R.layout.fragment_start
    override val viewModelClass: KClass<StartViewModel>
        get() = StartViewModel::class

    private val binding by viewBinding(FragmentStartBinding::bind)

    private lateinit var viewModel: StartViewModel

    override fun onBindViewModel(viewModel: StartViewModel) {
        navigate()
    }

    private fun navigate() {
        binding.logInButton.setOnClickListener {
            if (binding.inputUserNameEditText.text.toString().isNotEmpty()) {
                navigateSafe(StartFragmentDirections.actionStartFragmentToHomeFragment())
            }
        }
    }
}