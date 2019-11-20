package com.sideproject.manlihyang.side.contents.application

import io.fabric.sdk.android.Fabric
import com.crashlytics.android.Crashlytics
import android.app.Application
import com.sideproject.manlihyang.side.contents.di.module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.android.inject
import org.koin.core.context.startKoin

class ManLiHyangApplication : Application() {

    private val mCrashlytics : Crashlytics by inject()

    override fun onCreate() {
        super.onCreate()

        //Koin must be started first because of dependency injection
        initializeKoin()
        initializeFabric()
    }

    private fun initializeFabric() {
        Fabric.with(this, mCrashlytics)
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@ManLiHyangApplication)
            modules(module)
        }
    }
}