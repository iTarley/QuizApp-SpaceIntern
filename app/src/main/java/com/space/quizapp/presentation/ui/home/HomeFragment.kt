package com.space.quizapp.presentation.ui.home


import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentHomeBinding
import com.space.quizapp.presentation.ui.base.BaseFragment
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class HomeFragment : BaseFragment<HomeViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_home
    override val viewModelClass: KClass<HomeViewModel>
        get() = HomeViewModel::class

    private val binding by viewBinding(FragmentHomeBinding::bind)

    private lateinit var viewModel: HomeViewModel

    override fun onBindViewModel(viewModel: HomeViewModel) {
        navigate()
    }

    private fun navigate() {
        binding.blueGpaVectorView.setOnClickListener {
            navigateSafe(HomeFragmentDirections.actionHomeFragmentToShowPointFragment())
        }
        binding.logOutButton.setOnClickListener {
            navigateSafe(HomeFragmentDirections.actionHomeFragmentToStartFragment())
        }
    }
}