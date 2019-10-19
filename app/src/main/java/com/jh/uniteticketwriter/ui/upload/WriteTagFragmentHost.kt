package com.jh.uniteticketwriter.ui.upload

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs

import com.jh.uniteticketwriter.R

class WriteTagFragmentHost : Fragment() {
    private lateinit var viewModel: WriteTagViewModel
    private val args: WriteTagFragmentHostArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.write_tag_fragment_host, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(WriteTagViewModel::class.java)
        fragmentManager?.beginTransaction()?.replace(
            R.id.write_tag_fragment,
            viewModel.getFragmentByMessageType(args.messageTypeToWrite)
        )?.commit()
    }

}
