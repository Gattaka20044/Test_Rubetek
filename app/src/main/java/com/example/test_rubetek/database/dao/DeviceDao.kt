package com.example.test_rubetek.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.test_rubetek.Device

@Dao
interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(device: Device)

    @Delete
    suspend fun delete(device: Device)

    @Query("SELECT * from device_table")
    fun getAllDevices(): LiveData<List<Device>>


}