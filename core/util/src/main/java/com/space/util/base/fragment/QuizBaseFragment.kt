package com.space.util.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space.util.base.viewmodel.QuizBaseViewModel
import com.space.util.extensions.navigateSafe
import com.space.util.extensions.observe
import org.koin.androidx.viewmodel.ext.android.viewModelForClass
import kotlin.reflect.KClass

/** Base class for all fragments */

abstract class QuizBaseFragment<VM : QuizBaseViewModel> : Fragment() {

    protected abstract val layout: Int

    abstract val viewModelClass: KClass<VM>

    protected val viewModel: VM by viewModelForClass(clazz = viewModelClass)

    abstract fun onBind()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBind()
        observeNavigation()
    }

    private fun observeNavigation(){
        observe(viewModel.navigationState){
            if (it != null) {
                navigateSafe(findNavController(),it)
            }
        }
    }
}