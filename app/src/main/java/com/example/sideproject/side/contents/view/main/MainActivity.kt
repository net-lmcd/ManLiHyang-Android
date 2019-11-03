package com.example.sideproject.side.contents.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.sideproject.R
import io.grpc.ManagedChannelBuilder
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webSetting = webView.settings

        webSetting.javaScriptEnabled = true
        webSetting.loadWithOverviewMode = true

        webView.webViewClient = WebViewClient()


        webView.loadUrl("https://www.naver.com")
    }
}
