package com.example.test_rubetek.database.repository

import androidx.lifecycle.LiveData
import com.example.test_rubetek.Device

interface DeviceRepository {

    val allDevices: LiveData<List<Device>>
    suspend fun insertDevice(noteModel: Device)
}