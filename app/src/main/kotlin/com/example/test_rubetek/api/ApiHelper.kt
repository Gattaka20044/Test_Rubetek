package com.example.test_rubetek.api

import com.example.test_rubetek.RoomDevice

interface ApiHelper {

    suspend fun fetchRoomDevice(): RoomDevice

}