package com.sideproject.manlihyang.side.contents.view.main


import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.browser.customtabs.CustomTabsIntent

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentFirstBinding
import com.sideproject.manlihyang.databinding.FragmentFourthBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment

class FourthFragment : BaseFragment<FragmentFourthBinding>()  {

    override fun getLayoutId() = R.layout.fragment_fourth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://paul.kinlan.me/"
        var builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(view.context, Uri.parse(url))

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.bottom_nav_menu, menu)
    }

    companion object {
      fun instantiate() : FourthFragment{
          return FourthFragment()
      }
    }
}
