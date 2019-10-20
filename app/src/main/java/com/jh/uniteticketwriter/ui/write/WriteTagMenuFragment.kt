package com.jh.uniteticketwriter.ui.write

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.nfc.message.MessageNfcTypes
import com.jh.uniteticketwriter.ui.base.BaseViewModelFragment
import kotlinx.android.synthetic.main.write_tag_menu_fragment.*

class WriteTagMenuFragment : BaseViewModelFragment<WriteTagViewModel>(
    R.layout.write_tag_menu_fragment,
    WriteTagViewModel::class.java
) {

    private lateinit var menuViewModel: WriteTagMenuViewModel

    private fun navigateByType(type: MessageNfcTypes) {
        NavHostFragment.findNavController(this).let {
            val action = WriteTagMenuFragmentDirections
                .actionUploadFragmentToWriteTextFragment(type)
            it.navigate(action)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
    }

}
