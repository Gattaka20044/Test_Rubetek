package com.example.test_rubetek.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test_rubetek.App
import com.example.test_rubetek.Device
import com.example.test_rubetek.database.dao.DeviceDao

@Database(entities = [Device::class], version = 1)
abstract class DeviceDatabase: RoomDatabase() {
    abstract fun getDeviceDao(): DeviceDao

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
}