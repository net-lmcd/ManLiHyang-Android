package com.sideproject.manlihyang.side.contents.view.main


import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentWriteBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment

class WriteFragment : BaseFragment<FragmentWriteBinding>()  {

    override fun getLayoutId() = R.layout.fragment_write

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://paul.kinlan.me/"
        var builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(view.context, Uri.parse(url))
    }

    companion object {
      fun instantiate() : WriteFragment{
          return WriteFragment()
      }
    }
}
