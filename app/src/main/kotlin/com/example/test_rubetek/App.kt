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

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {



    override fun onCreate() {
        super.onCreate()
        app = this
        //initDatabase()
    }




    companion object{

        lateinit var repository: DeviceRepository

        fun getAllDevice(): LiveData<List<Device>> {
            return repository.allDevices
        }

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