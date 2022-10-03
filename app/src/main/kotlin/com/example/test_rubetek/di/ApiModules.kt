package com.example.test_rubetek.di

import com.example.test_rubetek.database.repository.DeviceRepository
import com.example.test_rubetek.ui.roomdevice.RoomDeviceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiModules = module {

    viewModel<RoomDeviceViewModel>{
        RoomDeviceViewModel(repository = get())
    }
}



