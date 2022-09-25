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
            // Android 10+
            cmg.getNetworkCapabilities(cmg.activeNetwork)?.let { networkCapabilities ->
                return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                        || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        } else {
            return cmg.activeNetworkInfo?.isConnectedOrConnecting == true
        }

        return false
    }

    fun initDatabase(){
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
}