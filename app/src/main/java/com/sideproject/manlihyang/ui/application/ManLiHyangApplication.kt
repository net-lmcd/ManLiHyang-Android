package com.sideproject.manlihyang.ui.application

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import com.kakao.auth.*
import com.sideproject.manlihyang.di.module
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ManLiHyangApplication : Application() {

    private val mCrashlytics : Crashlytics by inject()
    private val mMessaging: FirebaseMessaging by inject()

    override fun onCreate() {
        super.onCreate()

        //Koin must be started first because of dependency injection
        initializeKoin()
        initializeFabric()
        initializeFirebase()

        //Kakao Login
        instance = this
        KakaoSDK.init(KakaoSDKAdapter())
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@ManLiHyangApplication)
            modules(module)
        }
    }

    private fun initializeFabric() {
        Fabric.with(this, mCrashlytics)
    }

    private fun initializeFirebase() {
        FirebaseApp.initializeApp(this)
    }

    companion object {
        private var instance : ManLiHyangApplication? = null

        fun getManLiHyangApplicationContext(): ManLiHyangApplication {
            checkNotNull(instance) { "this application does not inherit com.kakao.GlobalApplication" }
            return instance!!
        }
    }
}