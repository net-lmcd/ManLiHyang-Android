package com.sideproject.manlihyang.side.contents.view.onboarding

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Base64.NO_WRAP
import android.util.Log
import android.widget.Toast
import com.facebook.*
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.kakao.auth.AuthType
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException
import com.kakao.util.helper.Utility.getPackageInfo
import com.sideproject.manlihyang.BR
import com.sideproject.manlihyang.R
import com.sideproject.manlihyang.databinding.ActivityLoginBinding
import com.sideproject.manlihyang.side.contents.view.SplashActivity
import com.sideproject.manlihyang.side.contents.base.BaseActivity
import com.sideproject.manlihyang.side.contents.base.BaseNavigator
import com.sideproject.manlihyang.side.contents.viewmodel.MoveVIewModel
import com.sideproject.manlihyang.side.contents.viewmodel.OnBoardingViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.*

class LoginActivity : BaseActivity<ActivityLoginBinding>(), BaseNavigator {

    override fun getLayoutId(): Int = R.layout.activity_login

    private val onBoardingViewModel: OnBoardingViewModel<BaseNavigator> by viewModel()
    private val moveVIewModel : MoveVIewModel<BaseNavigator> by viewModel()

    val callbackManager: CallbackManager? = CallbackManager.Factory.create()
    var sessionCallback: SessionCallback? = null

    override fun initViewModel() {
        onBoardingViewModel.setNavigator(this)
        moveVIewModel.setNavigator(this)
    }
    override fun initView() {
        binding.setVariable(BR.onBoardingModel, onBoardingViewModel)
        binding.setVariable(BR.moveModel, moveVIewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

         //to get HashKey from FacebookSdk, Because of default settings
 /*       if (BuildConfig.DEBUG) {
            FacebookSdk.setIsDebugEnabled(true);
            FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);
        }*/

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener {
                if (!it.isSuccessful) {
                    Log.e("LoginActivity", "getInstanceId failed", it.exception)
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = it.result?.token

                Log.e("LoginActivity", token)
            })
        facebook.setOnClickListener {
            val loginManager : LoginManager = LoginManager.getInstance()
            loginManager.apply {
                logInWithReadPermissions(this@LoginActivity, Arrays.asList("email","public_profile"))
                registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                    override fun onSuccess(result: LoginResult?) {
                        onBoardingViewModel.toMainActivity()
                    }
                    override fun onCancel() {}
                    override fun onError(error: FacebookException?) {}
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
                    Session.getCurrentSession().checkAndImplicitOpen()
                    Log.e("kakaotoken", "토큰 : " + Session.getCurrentSession().getTokenInfo().getAccessToken());
                    Log.e("kakaotoken", "토큰 리프레쉬토큰 : " + Session.getCurrentSession().getTokenInfo().getRefreshToken());
                    Log.e("kakaotoken", "토큰 파이어데이트 : " + Session.getCurrentSession().getTokenInfo().getRemainingExpireTime());
                }
            }
        }

        fb_logout.setOnClickListener{
            LoginManager.getInstance().logOut()
        }

        kakao_logout.setOnClickListener{
            logout()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(sessionCallback != null) {
            Session.getCurrentSession().removeCallback(sessionCallback)
        }
    }

    /**
     *   Facebook = {accessTokenTracker, loadUserProfile}
     */

    val accessTokenTracker = object : AccessTokenTracker(){
        override fun onCurrentAccessTokenChanged(
            oldAccessToken: AccessToken?,
            currentAccessToken: AccessToken?)
        {
            if(currentAccessToken!=null)
                loadUserProfile(currentAccessToken)
        }

    }

    fun loadUserProfile(accessToken: AccessToken){
        val graphRequest = GraphRequest.newMeRequest(accessToken, object : GraphRequest.GraphJSONObjectCallback {
            override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {
                `object`?.run {
                    val name = this.getString("name") ?: ""
                    //val email = this.getString("email") ?: ""
                    val id = this.getString("id") ?: return
                    val image = "https://graph.facebook.com/"+id+"/picture?type=normal"

                    //showDialogMessage("Facebook login\n" + "name: $name\n")
                }

            }
        })

        val parameters = Bundle()
        parameters.putString("field","first_name, last_name, email")
        graphRequest.parameters = parameters
        graphRequest.executeAsync()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //facebook
        callbackManager?.onActivityResult(requestCode, resultCode, data)

        //kakao
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            //Maybe when the hash key or android_native_key is invalid, it happens
            Toast.makeText(this@LoginActivity, "${exception?.message}",Toast.LENGTH_SHORT).show()
        }

        override fun onSessionOpened() {
            requestMe()
        }

        fun requestMe() {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {
                override fun onSuccess(result: MeV2Response?) {
                    /*Log.e(
                        "kakao onSuccess",
                        "requestMe onSuccess message : " + result?.getKakaoAccount()?.getEmail() + " " + result?.getId() + " " + result?.getNickname()
                    )
                    */
                    onBoardingViewModel.toMainActivity()
                }

                override fun onSessionClosed(errorResult: ErrorResult?) {
                    /*Log.e(
                        "kakao onSessionClosed",
                        "requestMe onSessionClosed message : " + errorResult?.getErrorMessage()
                    )*/
                    Toast.makeText(applicationContext, "${errorResult?.errorMessage}", Toast.LENGTH_SHORT).show()
                }

            })
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

    /**
     *  Hash
     */

    fun getHashKey(context: Context): String? {
        try {
            if (Build.VERSION.SDK_INT >= 28) {
                val packageInfo = getPackageInfo(context, PackageManager.GET_SIGNING_CERTIFICATES)
                val signatures = packageInfo.signingInfo.apkContentsSigners
                val md = MessageDigest.getInstance("SHA")
                for (signature in signatures) {
                    md.update(signature.toByteArray())
                    return String(Base64.encode(md.digest(), NO_WRAP))
                }
            } else {
                val packageInfo =
                    getPackageInfo(context, PackageManager.GET_SIGNATURES) ?: return null

                for (signature in packageInfo!!.signatures) {
                    try {
                        val md = MessageDigest.getInstance("SHA")
                        md.update(signature.toByteArray())
                        return Base64.encodeToString(md.digest(), NO_WRAP)
                    } catch (e: NoSuchAlgorithmException) {
                        Log.e("Unable to get MessageD", " $signature")
                    }
                }
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return null
    }

    fun onParams1(out : () -> String ) {

    }

    fun onParams2(out : (String) -> String ) {

    }
}