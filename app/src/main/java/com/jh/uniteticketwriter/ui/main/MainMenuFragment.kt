package com.jh.uniteticketwriter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.jh.uniteticketwriter.R
import kotlinx.android.synthetic.main.activity_main.*

class MainMenuFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.activity_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = findNavController(this)
        upload_button.setOnClickListener { navController.navigate(R.id.action_open_upload) }
        about_button.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_aboutFragment) }
        download_button.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_readFragment) }
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

}

