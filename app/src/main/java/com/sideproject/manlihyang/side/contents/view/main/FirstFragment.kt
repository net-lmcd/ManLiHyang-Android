package com.sideproject.manlihyang.side.contents.view.main


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentFirstBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel

class FirstFragment : BaseFragment<FragmentFirstBinding>()  {

    override fun getLayoutId() = R.layout.fragment_first

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://paul.kinlan.me/"
        var builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(view.context, Uri.parse(url))
    }

    companion object {
      fun instantiate() : FirstFragment{
          return FirstFragment()
      }
    }
}
