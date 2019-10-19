package com.jh.uniteticketwriter.ui.upload.wifi

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jh.uniteticketwriter.R

class WriteWifiApFragment : Fragment() {

    private lateinit var viewModel: WriteWifiApViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.write_wifi_ap_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WriteWifiApViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
