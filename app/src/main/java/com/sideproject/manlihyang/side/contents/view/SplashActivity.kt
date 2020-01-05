package com.sideproject.manlihyang.side.contents.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.transition.addListener
import androidx.databinding.DataBindingUtil
import com.sideproject.manlihyang.side.contents.view.onboarding.LoginActivity
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
            .root as ConstraintLayout

        setTheme(R.style.AppTheme)

        root.post{
            TransitionManager.beginDelayedTransition(root, AutoTransition().apply {
                duration = 2500L
                interpolator = AccelerateInterpolator()
                addListener(onEnd = {
                    startActivity(
                        Intent(applicationContext, LoginActivity::class.java)
                            .apply {
                                flags = flags or Intent.FLAG_ACTIVITY_CLEAR_TOP
                            }
                    )
                    finish()
                })
            })

            ConstraintSet().apply {
                clone(applicationContext, R.layout.activity_splash_end)
            }.applyTo(root)
        }
    }
}
