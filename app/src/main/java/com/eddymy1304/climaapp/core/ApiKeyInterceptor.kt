package com.eddymy1304.climaapp.core

import android.util.Log
import com.eddymy1304.climaapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class ApiKeyInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestInit = chain.request()
        val urlInit = requestInit.url

        val urlNew = urlInit.newBuilder()
            .addQueryParameter("appid", BuildConfig.API_KEY)
            .build()

        Log.d("Interceptor", urlNew.toString())

        val requestNew = requestInit.newBuilder()
            .url(urlNew)
            .build()

        return chain.proceed(requestNew)
    }
}