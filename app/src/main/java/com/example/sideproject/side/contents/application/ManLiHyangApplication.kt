package com.example.sideproject.side.contents.application

import android.app.Application
import com.example.sideproject.side.contents.di.module
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