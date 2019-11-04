package com.example.sideproject.side.contents.view.main

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.sideproject.R
import com.example.sideproject.databinding.ActivityMainBinding
import com.example.sideproject.side.contents.base.BaseActivity
import com.example.sideproject.side.contents.viewmodel.MainViewModel
import com.example.sideproject.side.contents.viewmodel.OnBoardingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel : MainViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webSetting = webView.settings

        webSetting.javaScriptEnabled = true
        webSetting.loadWithOverviewMode = true

        /**
         *  어떠한 것을 추가기능을 넣고싶을때 WebViewClient 이용함
         */
        webView.webViewClient = CustomWebViewClient()

        webView.loadUrl("https://www.naver.com")
    }


    class CustomWebViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Toast.makeText(view!!.context, "로딩 시작", Toast.LENGTH_SHORT).show()
        }
        override fun onPageFinished(view: WebView?, url: String?) {
            Toast.makeText(view!!.context, "로딩 끝", Toast.LENGTH_SHORT).show()
        }
    }
}
