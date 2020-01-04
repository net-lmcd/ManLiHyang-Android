package com.sideproject.manlihyang.side.contents.view.main


import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import com.sideproject.manlihyang.BR

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentWriteBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class WriteFragment : BaseFragment<FragmentWriteBinding, MainViewModel<MainNavigator>>()  {

    private val mainViewModel : MainViewModel<MainNavigator> by sharedViewModel()

    override fun getViewModel(): MainViewModel<MainNavigator> = mainViewModel
    override fun getBindingVariable(): Int = BR.mainModel
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
