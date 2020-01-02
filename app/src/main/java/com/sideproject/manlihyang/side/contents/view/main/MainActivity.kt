package com.sideproject.manlihyang.side.contents.view.main

import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityMainBinding
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.util.TypeofTab
import com.sideproject.manlihyang.side.contents.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(), MainNavigator {

    private val mainViewModel : MainViewModel<MainNavigator> by viewModel()

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun hasActionBar(): Boolean = false
    override fun hasBackIcon(): Boolean = false

    override fun initViewModel() {
        mainViewModel.setNavigator(this)
    }

    override fun initView() {
        bottomNavi.setOnNavigationItemSelectedListener(mainViewModel.bottomNaviSelectedListener)
        onNavigationTabSelected(TypeofTab.Content) // initialized at the first tab
    }

    override fun onNavigationTabSelected(tab: TypeofTab) {

        val currentFragment = supportFragmentManager.findFragmentByTag(tab.tag)
        supportFragmentManager.beginTransaction().apply {
            if(currentFragment==null)
                add(R.id.container,
                    when(tab) {
                        TypeofTab.Content -> ContentFragment.instantiate()
                        TypeofTab.Chat -> ContentFragment.instantiate()
                        TypeofTab.Write -> ContentFragment.instantiate()
                        TypeofTab.Mypage -> MyPageFragment.instantiate()
                    },
                    tab.tag
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
}
