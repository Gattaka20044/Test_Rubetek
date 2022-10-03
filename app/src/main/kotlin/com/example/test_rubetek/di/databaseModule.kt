package com.example.test_rubetek.di

import android.content.Context
import com.example.test_rubetek.database.database.DeviceDatabase
import com.example.test_rubetek.database.repository.DeviceRealization
import com.example.test_rubetek.database.repository.DeviceRepository
import org.koin.dsl.module

val databaseModule = module {

        fun initDatabase(context: Context):  DeviceRepository{
            val device = DeviceDatabase.getInstance(context).getDeviceDao()
            return DeviceRealization(device)

        }

    single<DeviceRepository>{initDatabase(context = get())}

}
