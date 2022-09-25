package com.example.test_rubetek

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import com.example.test_rubetek.api.ApiGSON
import com.example.test_rubetek.api.RetrofitInstance
import com.example.test_rubetek.database.database.DeviceDatabase
import com.example.test_rubetek.database.repository.DeviceRealization
import com.example.test_rubetek.database.repository.DeviceRepository
import com.example.test_rubetek.databinding.ItemRoomBinding
import com.example.test_rubetek.items.DeviceItem
import com.example.test_rubetek.items.MainCardItem
import com.xwray.groupie.viewbinding.BindableItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomDeviceViewModel(application: Application) : AndroidViewModel(application) {

    val roomDeviceLiveData: MutableLiveData<RoomDevice>
    val context = application
    lateinit var repository: DeviceRepository


    init {
        roomDeviceLiveData = MutableLiveData()
    }

    fun getResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitInstance.getRetrofitInstance().create(ApiGSON::class.java)
            val response = retroInstance.fetchRoomDevice()
            roomDeviceLiveData.postValue(response)

        }
    }

    fun getObserver(): MutableLiveData<RoomDevice> {
        return roomDeviceLiveData
    }

    fun internetCheck(c: Context): Boolean {
        val cmg = c.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        } else {
            return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
        }

        return false
    }

    fun initDatabase() {
        val device = DeviceDatabase.getInstance(context).getDeviceDao()
        repository = DeviceRealization(device)
    }

    fun getAllNotes(): LiveData<List<Device>> {
        return repository.allDevices
    }

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
