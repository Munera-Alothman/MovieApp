package com.example.movieapp.network

import com.example.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        //todo review : Authorization needs to be constant not hardcoded , define object for constants and scoped by context

        val request = chain.request().newBuilder()
            .addHeader(
                "Authorization",
                BuildConfig.BEARER_TOKEN
            )
            .build()

        return chain.proceed(request)
    }
}