package com.jh.uniteticketwriter.ui.upload.bluetooth

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jh.uniteticketwriter.R

class WriteBluetoothFragment : Fragment() {

    companion object {
        fun newInstance() = WriteBluetoothFragment()
    }

    private lateinit var viewModel: WriteBluetoothViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.write_bluetooth_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WriteBluetoothViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
