package com.sideproject.manlihyang.ui.activity

import android.os.Bundle
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityMainBinding
import com.sideproject.manlihyang.base.BaseActivity
import com.sideproject.manlihyang.ui.fragment.main.ChatFragment
import com.sideproject.manlihyang.ui.fragment.main.ContentFragment
import com.sideproject.manlihyang.ui.fragment.main.MyPageFragment
import com.sideproject.manlihyang.ui.fragment.main.WriteFragment
import com.sideproject.manlihyang.ui.navigator.MainNavigator
import com.sideproject.manlihyang.util.Dialog
import com.sideproject.manlihyang.util.MessageDialogClickListener
import com.sideproject.manlihyang.util.TypeofTab
import com.sideproject.manlihyang.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), MainNavigator {

    private val mViewModel : MainViewModel by viewModel()

    override val viewModel: MainViewModel
        get() = mViewModel

    override val layoutResId: Int
        get() = R.layout.activity_main

    override fun registerNavigator() {
        viewModel.setNavigator(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavi.setOnNavigationItemSelectedListener(viewModel.bottomNaviSelectedListener)
        onNavigationTabSelected(TypeofTab.Content) // initialized at the first tab
    }

    override fun onResume() {
        super.onResume()
        //changeMenuIconWithUserProfileImage()
    }

    override fun onNavigationTabSelected(tab: TypeofTab) {
        val currentFragment = supportFragmentManager.findFragmentByTag(tab.tag)
        supportFragmentManager.beginTransaction().apply {
            if(currentFragment==null)
                add(R.id.container,
                    when(tab) {
                        TypeofTab.Content -> ContentFragment.instantiate()
                        TypeofTab.Chat -> ChatFragment.instantiate()
                        TypeofTab.Write -> WriteFragment.instantiate()
                        TypeofTab.Mypage -> MyPageFragment.instantiate()
                    }, tab.tag
                )
            supportFragmentManager.fragments.forEach { fragment ->
                when {
                    fragment.tag != tab.tag -> hide(fragment)
                    fragment.isAdded -> show(fragment)
                    else -> add(R.id.container, fragment, tab.tag)
                }
            }
        }.commit()
    }

    private fun changeMenuIconWithUserProfileImage() {
        // Will be changed to a image of user.
        val menu = bottomNavi.menu
        menu.findItem(R.id.navigation_mypage).setIcon(R.drawable.profile_sample)
    }

    override fun onBackPressed() {
        showDialogMessage(
            message = "로그인 화면으로 돌아가시겠습니까?"
        )
        Dialog.showMessageConfirm(this, supportFragmentManager, "로그인 화면으로 돌아가시겠습니까?")
            .setMessageDialogClickListener(object : MessageDialogClickListener {
                override fun confirmClick() { finish() }
                override fun cancelClick() {}
            })
    }
}
