package com.space.quizapp.presentation.ui.points

import com.space.quizapp.R
import com.space.quizapp.databinding.FragmentShowPointBinding
import com.space.quizapp.presentation.ui.base.BaseFragment
import com.space.quizapp.utils.extensions.navigateSafe
import com.space.quizapp.utils.extensions.popBackStack
import com.space.quizapp.utils.extensions.viewBinding
import kotlin.reflect.KClass

class ShowPointFragment : BaseFragment<ShowPointViewModel>() {

    override val layout: Int
        get() = R.layout.fragment_show_point
    override val viewModelClass: KClass<ShowPointViewModel>
        get() = ShowPointViewModel::class

    private val binding by viewBinding(FragmentShowPointBinding::bind)

    private lateinit var viewModel: ShowPointViewModel

    override fun onBindViewModel(viewModel: ShowPointViewModel) {
        navigate()
    }

    private fun navigate(){
        binding.navBackImageButton.setOnClickListener {
            popBackStack(it)
        }
        binding.logOutButton.setOnClickListener {
            navigateSafe(ShowPointFragmentDirections.actionShowPointFragmentToStartFragment())
        }
    }
}