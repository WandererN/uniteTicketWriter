package com.jh.uniteticketwriter.ui.write

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment

import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes
import kotlinx.android.synthetic.main.write_tag_menu_fragment.*

class WriteTagMenuFragment : Fragment() {

    private lateinit var menuViewModel: WriteTagMenuViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.write_tag_menu_fragment, container, false)
    }

    private fun navigateByType(type: MessageNfcTypes) {
        NavHostFragment.findNavController(this).let {
            val action = WriteTagMenuFragmentDirections
                .actionUploadFragmentToWriteTextFragment(type)
            it.navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        menuViewModel = ViewModelProviders.of(this).get(WriteTagMenuViewModel::class.java)

        write_text_button.setOnClickListener {
            navigateByType(MessageNfcTypes.TEXT)
        }
        write_bluetooth_button.setOnClickListener {
            navigateByType(MessageNfcTypes.BLUETOOTH)
        }
        write_app_button.setOnClickListener {
            navigateByType(MessageNfcTypes.APP_LAUNCH)
        }
        write_wifi_button.setOnClickListener {
            navigateByType(MessageNfcTypes.WIFI)
        }
        // TODO: Use the ViewModel
    }

}
