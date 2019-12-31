package com.sideproject.manlihyang.side.contents.view.main

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.crashlytics.android.Crashlytics
import com.facebook.AccessToken
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityMainBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.util.TypeofTab
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import io.fabric.sdk.android.Fabric
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), MainNavigator {

    private val mCrashlytics : Crashlytics by inject()
    private val mainViewModel : MainViewModel<MainNavigator> by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViewModel() {
        mainViewModel.setNavigator(this)
    }

    override fun initView() {
        navigation.setOnNavigationItemSelectedListener(mainViewModel.navigationItemSelectedListener)
        onNavigationTabSelected(mainViewModel.currentTab)
    }

    override fun onNavigationTabSelected(tab: TypeofTab) {

        val webSetting = webView.settings
        webSetting.javaScriptEnabled = true
        webSetting.loadWithOverviewMode = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = CustomWebViewClient()

        var url : String = when(tab) {
            TypeofTab.Content -> "https://www.naver.com"
            TypeofTab.Chat    -> "https://www.google.com"
            TypeofTab.Write   -> "https://www.naver.com"
            TypeofTab.Mypage  -> "https://www.google.com"
        }
        webView.loadUrl(url)
    }

    /**
     *  어떠한 것을 추가기능을 넣고싶을때 WebViewClient 이용함
     */

    inner class CustomWebViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            showLoading()
        }
        override fun onPageFinished(view: WebView?, url: String?) {
            hideLoading()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode==KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
