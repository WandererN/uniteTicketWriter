package com.jh.uniteticketwriter.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders


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
        return inflater.inflate(layoutId, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        // TODO: Use the ViewModel
    }

}