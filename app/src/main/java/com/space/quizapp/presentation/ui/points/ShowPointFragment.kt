package com.space.quizapp.presentation.ui.points

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.space.quizapp.R

class ShowPointFragment : Fragment() {

    companion object {
        fun newInstance() = ShowPointFragment()
    }

    private lateinit var viewModel: ShowPointViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_show_point, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShowPointViewModel::class.java)
        // TODO: Use the ViewModel
    }

}