package com.sideproject.manlihyang.side.contents

import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_splash.*
import android.content.Intent
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.animation.AccelerateInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.transition.addListener
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget
import com.sideproject.manlihyang.side.contents.view.main.MainActivity
import com.sideproject.manlihyang.side.contents.view.onboarding.LoginActivity
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
            .root as ConstraintLayout

        //setTheme(R.style.Activities)

        root.post{
            TransitionManager.beginDelayedTransition(root, AutoTransition().apply {
                duration = 2000L
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

    /*
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        window.setFormat(PixelFormat.RGBA_8888)
    }

    private fun startAnimations() {

        lateinit var splashThread : Thread

        val gifImage = GlideDrawableImageViewTarget(splash)
        Glide.with(this).load(R.raw.splashgif).into(gifImage)

        var anim : Animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim.reset()
        lin_lay.clearAnimation()
        lin_lay.startAnimation(anim)

        anim = AnimationUtils.loadAnimation(this, R.anim.translate)
        anim.reset()
        splash.clearAnimation()
        splash.startAnimation(anim)

        splashThread = object : Thread() {
            override fun run() {
                try {
                    var waited = 0
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100)
                        waited += 100
                    }
                    val intent = Intent(
                        this@SplashActivity, MainActivity::class.java
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
                    startActivity(intent)
                    this@SplashActivity.finish()
                } catch (e: InterruptedException) {
                    // do nothing
                } finally {
                    this@SplashActivity.finish()
                }
            }
        }

        splashThread.start()

    }*/
}
