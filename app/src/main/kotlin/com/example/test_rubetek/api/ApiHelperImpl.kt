package com.example.test_rubetek.api

import com.example.test_rubetek.RoomDevice

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchRoomDevice(): RoomDevice = apiService.fetchRoomDevice()

}