package com.example.sideproject.side.contents.view.login

import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.sideproject.R
import kotlinx.android.synthetic.main.activity_splash.*
import android.content.Intent
import android.content.pm.ActivityInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget

class SplashActivity : AppCompatActivity() {

    lateinit var splashThread : Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startAnimations()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        window.setFormat(PixelFormat.RGBA_8888)
    }

    private fun startAnimations() {

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
                        this@SplashActivity, LoginActivity::class.java
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

    }
}
