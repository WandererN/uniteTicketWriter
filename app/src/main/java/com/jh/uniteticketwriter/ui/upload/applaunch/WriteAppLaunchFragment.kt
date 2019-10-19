package com.jh.uniteticketwriter.ui.upload.applaunch

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jh.uniteticketwriter.R

class WriteAppLaunchFragment : Fragment() {

    companion object {
        fun newInstance() = WriteAppLaunchFragment()
    }

    private lateinit var viewModel: WriteAppLaunchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.write_app_launch_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WriteAppLaunchViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
