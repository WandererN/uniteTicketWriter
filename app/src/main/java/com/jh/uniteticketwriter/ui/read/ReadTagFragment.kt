package com.jh.uniteticketwriter.ui.read

import android.content.Intent
import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.ui.IntentListenerFragment
import com.jh.uniteticketwriter.ui.base.BaseViewModelFragment

class ReadTagFragment :
    BaseViewModelFragment<ReadTagViewModel>(
        R.layout.activity_waiting_for_tag,
        ReadTagViewModel::class.java
    ),
    IntentListenerFragment {

    override fun onIntent(intent: Intent?) {
        viewModel.readTag(intent)
    }


}
