package com.jh.uniteticketwriter.ui.write.wifi

import android.os.Build
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import com.jh.uniteticketwriter.BR
import com.jh.uniteticketwriter.R
import com.jh.uniteticketwriter.nfc.message.wifi.AuthTypes
import com.jh.uniteticketwriter.ui.base.BaseViewModelFragment
import kotlinx.android.synthetic.main.write_wifi_ap_fragment.*

class WriteWifiApFragment : BaseViewModelFragment<WriteWifiApViewModel>(
    R.layout.write_wifi_ap_fragment,
    WriteWifiApViewModel::class.java
) {
    private lateinit var authTypesAdapter: ArrayAdapter<AuthTypes>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        authTypesAdapter = ArrayAdapter(this.context!!, android.R.layout.simple_list_item_1)
        authTypesAdapter.apply {
            add(AuthTypes.NO_AUTH)
            add(AuthTypes.WEP)
            add(AuthTypes.WPA2_PERSONAL)
            add(AuthTypes.WPA2_ENTERPRISE)
            notifyDataSetChanged()
        }
        viewModel.authType.observe(this, Observer {
            viewModel.userNameVisibility.set(it == AuthTypes.WPA2_ENTERPRISE)
            viewModel.passwordVisibility.set(it != AuthTypes.NO_AUTH)
        })
        wifi_ap_auth_type_spinner.setAdapter(authTypesAdapter)
        wifi_ap_auth_type_spinner.onItemClickListener =
            AdapterView.OnItemClickListener { _, _, position, _ ->
                viewModel.authType.value = authTypesAdapter.getItem(position)
            }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wifi_ap_auth_type_spinner.setText(authTypesAdapter.getItem(0)?.toString() ?: "", false)
        }
    }
}