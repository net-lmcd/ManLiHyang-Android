package com.example.sideproject.side.contents.view.main


import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent

import com.example.sideproject.R
import com.example.sideproject.databinding.FragmentFirstBinding
import com.example.sideproject.side.contents.base.BaseFragment
import com.example.sideproject.side.contents.viewmodel.MainViewModel

class FirstFragment : BaseFragment<FragmentFirstBinding, MainViewModel>()  {

    override fun getLayoutId() = R.layout.fragment_first

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

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
