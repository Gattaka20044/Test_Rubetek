package com.example.test_rubetek.ui.roomdevice

import android.app.Application
import androidx.lifecycle.*
import com.example.test_rubetek.App
import com.example.test_rubetek.Device
import com.example.test_rubetek.RoomDevice
import com.example.test_rubetek.api.ApiHelper
import com.example.test_rubetek.api.ApiService
import com.example.test_rubetek.database.database.DeviceDatabase
import com.example.test_rubetek.database.repository.DeviceRealization
import com.example.test_rubetek.database.repository.DeviceRepository
import com.example.test_rubetek.databinding.ItemRoomBinding
import com.example.test_rubetek.items.DeviceItem
import com.example.test_rubetek.items.MainCardItem
import com.example.test_rubetek.utils.NetworkHelper
import com.xwray.groupie.viewbinding.BindableItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RoomDeviceViewModel(private val networkHelper: NetworkHelper,
                          private val apiHelper: ApiHelper,
                          private val deviceDatabase: DeviceRealization
) : ViewModel() {


    private val _roomDeviceStateFlow = MutableStateFlow(RoomDevice(listOf()))
    val roomDeviceStateFlow: StateFlow<RoomDevice> = _roomDeviceStateFlow.asStateFlow()
    private var _response : MutableStateFlow<List<Device>> = MutableStateFlow(listOf(Device()))
    var response: StateFlow<List<Device>> = _response.asStateFlow()



    fun getResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = apiHelper.fetchRoomDevice()
            _roomDeviceStateFlow.value = response
        }
        getAllDevice()
    }


    fun getAllDevice() {
        viewModelScope.launch(Dispatchers.IO) {
            _response.value = deviceDatabase.allDevices()
        }
    }

    fun insert(device: Device) =
        viewModelScope.launch(Dispatchers.IO) {
            deviceDatabase.insertDevice(device)
        }

    fun sortDevice(
        roomDevice: List<Device>,
        roomListDevice: List<Device>,
        roomName: String
    ): List<Device> {
        val room = mutableListOf<String>()
        val roomSort = mutableListOf<Device>()
        val items = roomDevice.size

        for (item in 0 until items) {
            room.add(item, roomDevice[item].room)
        }

        var count = 0
        for (item in 0 until items) {
            if (roomName == roomListDevice[item].room) {

                roomSort.add(count, roomListDevice[item])
                count++
            }
        }
        return roomSort.toList()
    }

    fun sortRoom(roomDevice: List<Device>): List<String> {
        val itemsSet = roomDevice.size
        val room = mutableListOf<String>()
        for (item in 0 until itemsSet) {
            room.add(item, roomDevice[item].room)
        }
        return room.toSet().toList()
    }

    fun getDevice(
        sort: List<Device>,
        nameRoom: String
    ): BindableItem<ItemRoomBinding> {
        val device = mutableListOf<DeviceItem>()
        val items = sort.size
        for (item in 0 until items) {
            device.add(item, DeviceItem(setDevice(sort[item])))
        }
        return MainCardItem(nameRoom, device)

    }

    fun setDevice(item: Device): Device {
        return Device(
            id = item.id,
            `class` = item.`class`,
            icon = item.icon,
            name = item.name,
            room = item.room,
            hidden = item.hidden,
            favorite = item.favorite,
            disabled = item.disabled,
            online = item.online
        )
    }
}
