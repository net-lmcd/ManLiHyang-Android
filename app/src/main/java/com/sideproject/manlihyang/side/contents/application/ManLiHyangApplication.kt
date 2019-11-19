package com.sideproject.manlihyang.side.contents.application

import android.app.Application
import com.sideproject.manlihyang.side.contents.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ManLiHyangApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@ManLiHyangApplication)
            modules(module)
        }
    }
}