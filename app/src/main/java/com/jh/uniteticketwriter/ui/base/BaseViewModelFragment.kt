package com.jh.uniteticketwriter.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.jh.uniteticketwriter.BR


abstract class BaseViewModelFragment<T : ViewModel>(
    private val layoutId: Int,
    private val viewModelClass: Class<T>
) :
    Fragment() {

    lateinit var viewModel: T

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(viewModelClass)

        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            inflater, layoutId, container, false
        )
            ?: return inflater.inflate(layoutId, container, false)
        val view = binding.root
        //here data must be an instance of the class MarsDataProvider
        binding.setVariable(BR.viewModel, viewModel)
        return view
    }

}