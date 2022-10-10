package com.example.test_rubetek.database.repository

import androidx.lifecycle.LiveData
import com.example.test_rubetek.Device
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface DeviceRepository {

    val allDevices: LiveData<List<Device>>
    suspend fun insertDevice(device: Device)
}