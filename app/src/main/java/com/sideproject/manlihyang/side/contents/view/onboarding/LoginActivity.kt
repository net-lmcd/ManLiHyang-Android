package com.sideproject.manlihyang.side.contents.view.onboarding

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import com.crashlytics.android.Crashlytics
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.BuildConfig
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityLoginBinding
import com.sideproject.manlihyang.side.contents.SplashActivity
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest
import java.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(), BaseNavigator {

    private val mCrashlytics: Crashlytics by inject()
    override fun getLayoutId(): Int = R.layout.activity_login

    private val onBoardingViewModel: OnBoardingViewModel<BaseNavigator> by viewModel()

    lateinit var callbackManager: CallbackManager
    lateinit var sessionCallback: SessionCallback

    //before initializing view
    override fun initViewModel() {
        onBoardingViewModel.setNavigator(this)
    }

    //after initalizing viewmodel
    override fun initView() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // to get HashKey from FacebookSdk, Because of default settings
        if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }

        callbackManager = CallbackManager.Factory.create()

        facebook.setOnClickListener {
            LoginManager.getInstance().logInWithReadPermissions(
                this,
                Arrays.asList("public_profile")
            )
            LoginManager.getInstance().apply {
                registerCallback(callbackManager,
                    object : FacebookCallback<LoginResult> {
                        override fun onSuccess(result: LoginResult?) {
                            // Can get access token in here the followign stuff
                            Log.e("getAccessToken()", AccessToken.getCurrentAccessToken().toString())
                            Log.e("getAccessToken()", AccessToken.USER_ID_KEY)
                            Log.e("getAccessToken()", AccessToken.ACCESS_TOKEN_KEY)
                            Log.e("getAccessToken()", AccessToken.isCurrentAccessTokenActive().toString())
                            Log.e("getAccessToken()", AccessToken.isDataAccessActive().toString())

                            val request : GraphRequest = GraphRequest.newMeRequest(result?.accessToken ,
                                object : GraphRequest.GraphJSONObjectCallback {
                                    override fun onCompleted(
                                        `object`: JSONObject?,
                                        response: GraphResponse?
                                    ) {
                                        Log.e("facebook user profile", `object`.toString())
                                    }
                                })
                            var parameters = Bundle()
                            parameters.putString("fields","id,name,email,gender,birthday")
                            request.parameters = parameters
                            request.executeAsync()

                            onBoardingViewModel.toMainActivity()
                        }

                        override fun onCancel() {}
                        override fun onError(error: FacebookException?) {
                            Log.e("Login error on the fb", error.toString())
                        }

                    })
            }
        }

        kakao.setOnClickListener {
            val session: Session = Session.getCurrentSession()
            session.apply {
                sessionCallback = SessionCallback()
                addCallback(sessionCallback)
                open(AuthType.KAKAO_LOGIN_ALL, this@LoginActivity)

                if(Session.getCurrentSession().isOpenable()){
                    Session.getCurrentSession().checkAndImplicitOpen();
                }

                Log.e("kakaotoken", "토큰 : " + Session.getCurrentSession().getTokenInfo().getAccessToken());
                Log.e("kakaotoken", "토큰 리프레쉬토큰 : " + Session.getCurrentSession().getTokenInfo().getRefreshToken());
                Log.e("kakaotoken", "토큰 파이어데이트 : " + Session.getCurrentSession().getTokenInfo().getRemainingExpireTime());
            }
        }

        fb_logout.setOnClickListener{
            LoginManager.getInstance().logOut()
        }

        kakao_logout.setOnClickListener{
            logout()
        }
    }

    override fun onResume() {
        super.onResume()
        // Set variable for binding
        binding.setVariable(BR.onBoardingModel, onBoardingViewModel)

        onBoardingViewModel.setNavigator(this)

        getHashKey()
        Log.e("aaaaresume","resume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(sessionCallback)
        Log.e("aaaadestroy","destroy")
    }

    override fun onStart() {
        super.onStart()

        Log.e("aaaastart","start")
    }

    override fun onPause() {
        super.onPause()

        Log.e("aaaapause","pause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("aaaastop","stop")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //kakao
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) { return; }
        super.onActivityResult(requestCode, resultCode, data);

        //facebook
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    fun hideKeyboardFromEditText(view: View) {
        hideKeyboardChildAswell(view)
    }

    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            //Maybe when the hash key or android_native_key is invalid, it happens
            Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception?.message);
        }

        override fun onSessionOpened() {
            Log.e("asdf", "카카오 로그인 성공")
            requestMe()
        }

        fun requestMe() {

            var keys: ArrayList<String> = arrayListOf();
            keys.add("properties.nickname");
            keys.add("properties.profile_image");
            keys.add("kakao_account.email");

            UserManagement.getInstance().me(keys, object : MeV2ResponseCallback() {
                override fun onSuccess(result: MeV2Response?) {
                    Log.e(
                        "asdf",
                        "requestMe onSuccess message : " + result?.getKakaoAccount()?.getEmail() + " " + result?.getId() + " " + result?.getNickname()
                    )
                    onBoardingViewModel.toMainActivity()
                }

                override fun onSessionClosed(errorResult: ErrorResult?) {
                    Log.e(
                        "asdf",
                        "requestMe onSessionClosed message : " + errorResult?.getErrorMessage()
                    )
                }

            })
        }
    }

    fun getHashKey() {
        try {
            val info = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNING_CERTIFICATES)
            val signatures = info.signingInfo.apkContentsSigners
            val md = MessageDigest.getInstance("SHA")
            for (signature in signatures) {
                val md: MessageDigest
                md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                val key = String(Base64.encode(md.digest(), 0))
                Log.d("Hash key:", "$key")
            }
        } catch(e: Exception) {
            Log.e("name not found", e.toString())
        }
    }

    fun logout() {
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {
                startActivity(Intent(applicationContext, SplashActivity::class.java));
            }

            override fun onNotSignedUp() {
                super.onNotSignedUp()
            }

            override fun onSessionClosed(errorResult: ErrorResult?) {
                super.onSessionClosed(errorResult)
            }
        });
    }

}