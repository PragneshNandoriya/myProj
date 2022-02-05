package com.example.myapplication6

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClass {
    private var httpLoggingInterceptor:HttpLoggingInterceptor = HttpLoggingInterceptor().
    setLevel(HttpLoggingInterceptor.Level.BODY)
    private val okHttpClient:OkHttpClient = OkHttpClient.Builder().readTimeout(10, TimeUnit.SECONDS).addInterceptor(httpLoggingInterceptor).build()
   private val retrofit:Retrofit = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").
    addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()

    fun getApiInterface():ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }
}