package com.jh.uniteticketwriter.ui.write

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.ui.base.BaseViewModelFragment

class WriteTagFragmentHost : BaseViewModelFragment<WriteTagViewModel>(
    R.layout.write_tag_fragment_host,
    WriteTagViewModel::class.java
) {

    private val args: WriteTagFragmentHostArgs by navArgs()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fragment = viewModel.getFragmentByMessageType(args.messageTypeToWrite)
        fragmentManager?.beginTransaction()?.replace(
            R.id.write_tag_fragment,
            fragment
        )?.commit()
    }

}
