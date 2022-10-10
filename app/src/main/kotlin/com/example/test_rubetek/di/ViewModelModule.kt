package com.example.test_rubetek.di


import com.example.test_rubetek.ui.roomdevice.RoomDeviceViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        RoomDeviceViewModel(networkHelper = get(), apiHelper =  get(), deviceDatabase =  get())
    }
}
