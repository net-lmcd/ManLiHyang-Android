package com.sideproject.manlihyang.ui.fragment.main

import android.graphics.Bitmap
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sideproject.manlihyang.BR

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentChatBinding
import com.sideproject.manlihyang.base.BaseFragment
import com.sideproject.manlihyang.ui.navigator.MainNavigator
import com.sideproject.manlihyang.ui.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ChatFragment : BaseFragment<FragmentChatBinding, MainViewModel<MainNavigator>>()  {

    private val mainViewModel : MainViewModel<MainNavigator> by sharedViewModel()

    override fun getLayoutId() = R.layout.fragment_chat
    override fun getViewModel(): MainViewModel<MainNavigator> = mainViewModel
    override fun getBindingVariable(): Int = BR.mainModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://www.naver.com/"
        /*var builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(view.context, Uri.parse(url))*/
        val webSetting = binding!!.webView.settings
        webSetting.javaScriptEnabled = true
        webSetting.loadWithOverviewMode = true
        binding!!.webView.apply {
            setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(p0: View?, p1: Int, p2: KeyEvent?): Boolean {
                    if (p2?.getAction() != KeyEvent.ACTION_DOWN)
                        return true;
                    if (p1 == KeyEvent.KEYCODE_BACK) {
                        if (this@apply.canGoBack()) {
                            this@apply.goBack()
                        } else {
                            activity?.onBackPressed()
                        }
                        return true;
                    }
                    return false;
                }
            })
            webChromeClient = WebChromeClient()
            webViewClient = CustomWebViewClient()
            loadUrl(url)
        }
    }

    inner class CustomWebViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            showLoading()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            hideLoading()
        }
    }

    companion object {
        fun instantiate() : ChatFragment{
            return ChatFragment()
        }
    }
}