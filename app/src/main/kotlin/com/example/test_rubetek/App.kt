package com.example.test_rubetek

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.test_rubetek.di.apiModules
import com.example.test_rubetek.di.databaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {



    override fun onCreate() {
        super.onCreate()
        app = this

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    apiModules,
                    databaseModule
                )
            )
        }
    }




    companion object{


        fun internetCheck(c: Context): Boolean {
            val cmg = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
                    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                            || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                }
            } else {
                return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
            }

            return false
        }
        val baseURL = "https://gist.githubusercontent.com/"
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        private lateinit var app: App
        val instance: App get() = app

    }

}