package com.example.test_rubetek

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LiveData
import com.example.test_rubetek.database.database.DeviceDatabase
import com.example.test_rubetek.database.repository.DeviceRealization
import com.example.test_rubetek.database.repository.DeviceRepository
import com.example.test_rubetek.di.appModule
import com.example.test_rubetek.di.databaseModule
import com.example.test_rubetek.di.viewModelModule
import org.koin.android.ext.koin.androidContext


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import org.koin.core.context.startKoin

class App : Application() {



    override fun onCreate() {
        super.onCreate()
        app = this

        startKoin{
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    databaseModule,
                    viewModelModule
                )
            )
        }
    }


    companion object{

//        lateinit var repository: DeviceRepository

//        fun getAllDevice(): LiveData<List<Device>> {
//            return repository.allDevices
//        }

        private lateinit var app: App
        val instance: App get() = app

    }

}