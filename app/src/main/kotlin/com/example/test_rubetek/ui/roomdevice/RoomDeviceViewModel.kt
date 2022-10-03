package com.example.test_rubetek.ui.roomdevice

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_rubetek.App
import com.example.test_rubetek.Device
import com.example.test_rubetek.RoomDevice
import com.example.test_rubetek.api.ApiGSON
import com.example.test_rubetek.database.database.DeviceDatabase
import com.example.test_rubetek.database.repository.DeviceRealization
import com.example.test_rubetek.database.repository.DeviceRepository
import com.example.test_rubetek.databinding.ItemRoomBinding
import com.example.test_rubetek.items.DeviceItem
import com.example.test_rubetek.items.MainCardItem
import com.xwray.groupie.viewbinding.BindableItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

//class RoomDeviceViewModel(application: Application) : AndroidViewModel(application) {
class RoomDeviceViewModel(private val repository: DeviceRepository) : ViewModel() {

    private val _roomDeviceStateFlow = MutableStateFlow(RoomDevice(listOf()))
    val roomDeviceStateFlow: StateFlow<RoomDevice> = _roomDeviceStateFlow.asStateFlow()
//    private var _response : MutableStateFlow<List<Device>> = MutableStateFlow(emptyList())
//    var response: StateFlow<List<Device>> = _response.asStateFlow()






//    init {
//        roomDeviceLiveData = MutableLiveData()
//    }

    fun getResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = App.getRetrofitInstance().create(ApiGSON::class.java)
            val response = retroInstance.fetchRoomDevice()
            _roomDeviceStateFlow.value = response
        }
    }





    fun getAllDevice(): LiveData<List<Device>> {
        return repository.allDevices
    }

//    fun getAllDevice() {
//
//        _response.value = repository.allDevices
//
//    }
//    fun getAllDevice(): List<Device> {
//        var response = listOf<Device>()
//        viewModelScope.launch(Dispatchers.IO) {
//           response = repository.allDevices
//        }
//        return response
//    }

    fun insert(device: Device) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDevice(device)
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
