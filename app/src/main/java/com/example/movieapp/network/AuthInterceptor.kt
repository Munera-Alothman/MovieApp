package com.example.movieapp.network

import com.example.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                BuildConfig.BEARER_TOKEN
            )
            .build()

        return chain.proceed(request)
    }
}