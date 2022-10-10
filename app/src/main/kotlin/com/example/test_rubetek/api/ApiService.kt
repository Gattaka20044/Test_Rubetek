package com.example.test_rubetek.api

import com.example.test_rubetek.RoomDevice
import retrofit2.http.GET

interface ApiService {

    @GET("goderfly/87840e25312da5c37e3c3afab8417012/raw/c57d14af26577c0ffad8d680882e63a56c2b4692/house.json")
    suspend fun fetchRoomDevice(): RoomDevice

}