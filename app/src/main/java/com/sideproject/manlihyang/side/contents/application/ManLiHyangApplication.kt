package com.sideproject.manlihyang.side.contents.application

import android.app.Application
import android.content.Context
import com.crashlytics.android.Crashlytics
import com.kakao.auth.*
import com.sideproject.manlihyang.side.contents.di.module
import io.fabric.sdk.android.Fabric
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class ManLiHyangApplication : Application() {

    private val mCrashlytics : Crashlytics by inject()

    override fun onCreate() {
        super.onCreate()

        //Koin must be started first because of dependency injection
        initializeKoin()
        initializeFabric()

        //Kakao Login
        instance = this
        KakaoSDK.init(KakaoSDKAdapter())
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

    fun getManLiHyangApplicationContext(): ManLiHyangApplication {
        checkNotNull(instance) { "this application does not inherit com.kakao.GlobalApplication" }
        return instance!!
    }

    companion object {
        private var instance : ManLiHyangApplication? = null
        private class KakaoSDKAdapter : KakaoAdapter() {

/*            override fun getApplicationConfig(): IApplicationConfig? {
                return object : IApplicationConfig {
                    override fun getApplicationContext(): Context {
                        return ManLiHyangApplication.getManLiHyangApplicationContext()
                    }
                }
            }*/

            override fun getApplicationConfig(): IApplicationConfig {
                return IApplicationConfig {
                    instance?.getManLiHyangApplicationContext()
                }
            }

            override fun getSessionConfig(): ISessionConfig? {
                return object : ISessionConfig {
                    override fun getAuthTypes(): Array<AuthType> {
                        return arrayOf(AuthType.KAKAO_LOGIN_ALL)
                    }

                    override fun isUsingWebviewTimer(): Boolean {
                        return false
                    }

                    override fun isSecureMode(): Boolean {
                        return false
                    }

                    override fun getApprovalType(): ApprovalType? {
                        return ApprovalType.INDIVIDUAL
                    }

                    override fun isSaveFormData(): Boolean {
                        return true
                    }
                }
            }
        }
    }
}