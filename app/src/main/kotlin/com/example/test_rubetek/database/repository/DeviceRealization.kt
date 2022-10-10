package com.example.test_rubetek.database.repository

import androidx.lifecycle.LiveData
import com.example.test_rubetek.Device
import com.example.test_rubetek.database.dao.DeviceDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

class DeviceRealization(private val deviceDao: DeviceDao) : DeviceRepository {

    override val allDevices: LiveData<List<Device>>
        get() = deviceDao.getAllDevices()

    override suspend fun insertDevice(device: Device) {
        deviceDao.insert(device)
    }



}