package com.example.test_rubetek

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "device_table")
data class Device(
//    val id: String,
//    val `class`: String,
//    val type: String,
//    val icon: String,
//    val name: String,
//    val room: String,
//    val hidden: Boolean,
//    val favorite: Boolean,
//    val disabled: Boolean,
//    val online: Boolean

    @PrimaryKey(autoGenerate = false)
    var id: String = "",
    var `class`: String = "",
    var icon: String = "",
    var name: String = "",
    var room: String = "",
    var hidden: Boolean = false,
    var favorite: Boolean = false,
    var disabled: Boolean = false,
    var online: Boolean = false,
    )