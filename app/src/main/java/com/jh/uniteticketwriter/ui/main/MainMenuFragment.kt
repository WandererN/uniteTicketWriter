package com.jh.uniteticketwriter.ui.main

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.ui.base.BaseViewModelFragment
import kotlinx.android.synthetic.main.main_menu_fragment.*

class MainMenuFragment :
    BaseViewModelFragment<MainViewModel>(R.layout.main_menu_fragment, MainViewModel::class.java) {

    private lateinit var navController: NavController

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = findNavController(this)
        upload_button.setOnClickListener { navController.navigate(R.id.action_open_upload) }
        about_button.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_aboutFragment) }
        download_button.setOnClickListener { navController.navigate(R.id.action_mainFragment_to_readFragment) }
    }

}

