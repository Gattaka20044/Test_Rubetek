package com.example.test_rubetek.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.test_rubetek.App
import com.example.test_rubetek.Device
import com.example.test_rubetek.api.ApiHelper
import com.example.test_rubetek.api.ApiHelperImpl
import com.example.test_rubetek.api.ApiService
import com.example.test_rubetek.database.dao.DeviceDao
import com.example.test_rubetek.database.database.DeviceDatabase
import com.example.test_rubetek.database.repository.DeviceRealization
import com.example.test_rubetek.database.repository.DeviceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.dsl.module
import retrofit2.Retrofit

val databaseModule = module {

    single { provideDatabase(application = get()) }
    single { provideDao(get()) }

}

//val repositoryModule = module {
//    fun provideUserRepository( dao: UserDao): UserRepository {
//        return UserRepository(dao)
//    }
//
//    single { provideUserRepository(get(), get()) }
//}

//fun initDatabase(context: Context):  DeviceRepository{
//    val device = DeviceDatabase.getInstance(context).getDeviceDao()
//    return DeviceRealization(device)
//
//}


//fun insert(device: Device) =
//    viewModelScope.launch(Dispatchers.IO) {
//        App.repository.insertDevice(device)
//    }

private fun provideDatabase(application: Context): DeviceDatabase {
    return Room.databaseBuilder(application, DeviceDatabase::class.java,"database").build()
}

fun provideDao(database: DeviceDatabase): DeviceDao {
    return database.getDeviceDao()
}

//fun provideDao(): DeviceRepository {
//    return DeviceRepository
//}


//    companion object{
//        private var database: DeviceDatabase ?= null
//        @Synchronized
//        fun getInstance(context: Context): DeviceDatabase{
//            return if (database == null){
//                database = Room.databaseBuilder(context, DeviceDatabase::class.java, "database").build()
//                database as DeviceDatabase
//            }else{
//                database as DeviceDatabase
//            }
//        }
//    }