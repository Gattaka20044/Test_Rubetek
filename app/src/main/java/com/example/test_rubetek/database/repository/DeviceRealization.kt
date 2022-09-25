package com.example.test_rubetek.database.repository

import androidx.lifecycle.LiveData
import com.example.test_rubetek.Device
import com.example.test_rubetek.database.dao.DeviceDao

class DeviceRealization(private val deviceDao: DeviceDao): DeviceRepository {

    override val allDevices: LiveData<List<Device>>
        get() = deviceDao.getAllDevices()

    override suspend fun insertDevice(noteModel: Device) {
        deviceDao.insert(noteModel)
    }

//    override suspend fun deleteNote(noteModel: Device, onSuccess: () -> UInt) {
//        deviceDao.delete(noteModel)
//    }



}