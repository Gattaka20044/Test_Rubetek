package com.example.test_rubetek.di


import android.content.Context
import androidx.room.Room
import com.example.test_rubetek.database.dao.DeviceDao
import com.example.test_rubetek.database.database.DeviceDatabase
import com.example.test_rubetek.database.repository.DeviceRealization

import org.koin.dsl.module


val databaseModule = module {

    single { provideDatabase(application = get()) }
    single { provideDao(get()) }
    single { provideRealization(get()) }

}

private fun provideRealization(deviceDao: DeviceDao): DeviceRealization {
    return DeviceRealization(deviceDao)
}

private fun provideDatabase(application: Context): DeviceDatabase {
    return Room.databaseBuilder(application, DeviceDatabase::class.java,"database").build()
}

fun provideDao(database: DeviceDatabase): DeviceDao {
    return database.getDeviceDao()
}
