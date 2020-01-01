package com.sideproject.manlihyang.side.contents.view.main


import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.browser.customtabs.CustomTabsIntent

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.FragmentFirstBinding
import com.sideproject.manlihyang.side.contents.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_first.*

class FirstFragment : BaseFragment<FragmentFirstBinding>() {

    override fun getLayoutId() = R.layout.fragment_first

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = "https://www.naver.com"

        val webSetting = webView.settings
        webSetting.javaScriptEnabled = true
        webSetting.loadWithOverviewMode = true
        webView.apply {
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
            webViewClient = WebViewClient()
            loadUrl(url)
        }
    }

    /**
     *  어떠한 것을 추가기능을 넣고싶을때 WebViewClient 이용함
     */

    inner class CustomWebViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            //showLoading()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            //hideLoading()
        }
    }

    companion object {
        fun instantiate(): FirstFragment {
            return FirstFragment()
        }
    }
}
