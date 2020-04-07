package com.sideproject.manlihyang.util

import android.content.Context
import android.content.Intent
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sideproject.manlihyang.BuildConfig
import com.sideproject.manlihyang.data.local.PreferenceManager
import com.sideproject.manlihyang.model.local.AuthResponse
import com.sideproject.manlihyang.data.repository.OnBoardingApi
import com.sideproject.manlihyang.ui.activity.LoginActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object ApiService {

    var connectionTime = 1
    private var mRetrofit: Retrofit? = null
    private var mRerfoitWithoutAuth: Retrofit? = null

    private fun provideRetrofitWithoutAuth(): Retrofit? {
        if (mRerfoitWithoutAuth == null) {
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
            mRerfoitWithoutAuth = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(provideOKHttpClientWithoutAuth())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
               // .addConverterFactory(EnumConverterFactory())
                .build()
        }
        return mRerfoitWithoutAuth
    }

    private fun provideOKHttpClientWithoutAuth(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(provideHttpLoggingInterceptor())
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    fun provideRetrofit(context: Context): Retrofit? {
        if (mRetrofit == null) {
            val gson = GsonBuilder()
                .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create()
            mRetrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(provideOkHttpClient(context))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
              //  .addConverterFactory(EnumConverterFactory())
                .build()
        }
        return mRetrofit
    }

    private fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
       //     .addInterceptor(provideAuthInterceptor(context))
            .addInterceptor(provideHttpLoggingInterceptor())   // log로 request, response 확인
            .addNetworkInterceptor(StethoInterceptor())
            .build()
    }

    private fun provideApiKeyInterceptor(context: Context?): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val urlBuilder = originalHttpUrl.newBuilder()
            val url = urlBuilder.build()
            val requestBuilder = original.newBuilder()
                .url(url)
            if (context != null) {
                val accessToken: AuthResponse? =
                    PreferenceManager.getInstance(context).getAccessToken()
                if (accessToken != null) {
                    requestBuilder.addHeader(
                        "Authorization",
                        "Bearer " + accessToken.accessToken
                    )
                }
            }
            val request = requestBuilder.build()
            chain.proceed(request)
        }
    }

    private fun provideAuthInterceptor(context: Context): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()
            val urlBuilder = originalHttpUrl.newBuilder()
            val url = urlBuilder.build()
            val requestBuilder = original.newBuilder()
                .url(url)
            if (context != null) {
                val accessToken: AuthResponse? =
                    PreferenceManager.getInstance(context).getAccessToken()
                if (accessToken != null) {
                    if (!url.url().toString().endsWith("refresh")) {
                      //  if (url.url().toString().contains(BuildConfig.BASE_URL_FOR_CHECK)) {
                            requestBuilder.addHeader(
                                "Authorization",
                                "Bearer " + accessToken.accessToken
                            )
                      // }
                    }
                }
            }
            // try the request
            val response = chain.proceed(requestBuilder.build())
            createWellDoneResponse(response, chain, requestBuilder, context)
        }
    }

    private fun createWellDoneResponse(
        response: okhttp3.Response,
        chain: Interceptor.Chain,
        requestBuilder: Request.Builder,
        context: Context
    ): okhttp3.Response {
        if (response.code() == 401) {
            connectionTime++
            if (connectionTime < 10) {
                val token: AuthResponse? = PreferenceManager.getInstance(context).getAccessToken()
                // create a new request and modify it accordingly using the new token
//                token.setUser(null);
                var refreshToken: Response<AuthResponse?>? = null
                try {
                    refreshToken = provideApiWithoutAuth(OnBoardingApi::class.java)
                        .refreshToken(token!!).execute()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                assert(refreshToken != null)
                if (refreshToken!!.code() == 201) {
                    connectionTime = 1
                    val newtoken: AuthResponse = refreshToken.body()!!
                    if (newtoken.accessToken != null) {
                        token?.refreshToken = newtoken.refreshToken
                    }
                    if (newtoken.refreshToken != null) {
                        token?.refreshToken = newtoken.refreshToken
                    }
                    PreferenceManager.getInstance(context).setAccessToken(Gson().toJson(token))
                    requestBuilder.removeHeader("Authorization")
                    if (newtoken.accessToken != null) {
                        requestBuilder.addHeader(
                            "Authorization",
                            "Bearer " + newtoken.accessToken
                        )
                    }
                    val request = requestBuilder.build()
                    try { //                        return createWellDoneResponse(newresponse, chain, requestBuilder, context);
                        return chain.proceed(request)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                } else {
                    logoutFromToken(context)
                    //                    return createWellDoneResponse(response, chain, requestBuilder, context);
                }
            } else { //                Function.logoutFromToken(context, PreferencesManager.getInstance(context).getUser().getUserSeq());
                return response
            }
            return response
        }
        return response
    }

    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        val logLevel: HttpLoggingInterceptor.Level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        interceptor.setLevel(logLevel)
        return interceptor
    }

    fun <T> provideApi(service: Class<T>?, context: Context): T {
        return provideRetrofit(context)!!.create(service)
    }

    fun <T> provideApiWithoutAuth(service: Class<T>?): T {
        return provideRetrofitWithoutAuth()!!.create(service)
    }

    fun logoutFromToken(context: Context) {
        PreferenceManager.getInstance(context).remove(
            PreferenceManager.Key.signed)
        PreferenceManager.getInstance(context).remove(
            PreferenceManager.Key.accessToken)
        PreferenceManager.getInstance(context).clear()
        val intent = Intent(context, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
