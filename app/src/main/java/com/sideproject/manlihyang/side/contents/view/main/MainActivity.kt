package com.sideproject.manlihyang.side.contents.view.main

import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityMainBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.util.TypeofTab
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), MainNavigator {

    private val mMainViewModel : MainViewModel by viewModel()
    private val mOnBoardingViewModel : OnBoardingViewModel by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mMainViewModel.setNavigator(this)

        binding.bottomNavView.setOnNavigationItemSelectedListener(mMainViewModel.navigationItemSelectedListener)
        onNavigationTabSelected(mMainViewModel.currentTab)
    }

    override fun onNavigationTabSelected(tab: TypeofTab) {

        val webSetting = webView.settings
        webSetting.javaScriptEnabled = true
        webSetting.loadWithOverviewMode = true
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

    class CustomWebViewClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            Toast.makeText(view!!.context, "로딩 시작", Toast.LENGTH_SHORT).show()
        }
        override fun onPageFinished(view: WebView?, url: String?) {
            Toast.makeText(view!!.context, "로딩 끝", Toast.LENGTH_SHORT).show()
        }

    }
}
