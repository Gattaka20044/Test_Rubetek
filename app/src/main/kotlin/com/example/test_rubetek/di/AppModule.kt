package com.example.test_rubetek.di

import android.content.Context
import com.example.test_rubetek.BuildConfig

import com.example.test_rubetek.api.ApiHelper
import com.example.test_rubetek.api.ApiHelperImpl
import com.example.test_rubetek.api.ApiService
import com.example.test_rubetek.utils.NetworkHelper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://gist.githubusercontent.com/"

val appModule = module {
    single { provideRetrofit(BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideRetrofit(BASE_URL: String) : Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)