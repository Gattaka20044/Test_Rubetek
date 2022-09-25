package com.example.test_rubetek

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_layout.view.*


class DeviceAdapter: RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>() {

    var listDevice = emptyList<Device>()

    class DeviceViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return DeviceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.itemView.apply {
            room.text = listDevice[position].room
            name_device.text = listDevice[position].name
            if (listDevice[position].online){
                online_device.text = "Online"
            }else{
                online_device.text = "Offline"
            }
            val image = when (listDevice[position].icon) {
                "water_sensor" -> R.drawable.ic_water_sensor
                "pir_sensor" -> R.drawable.ic_pir_sensor
                "power_strip_3" -> R.drawable.ic_power_strip_3
                "rf_humidity" -> R.drawable.ic_rf_humidity
                "light_sensor" -> R.drawable.ic_light_sensor
                "temperature_humidity_sensor" -> R.drawable.ic_temperature_humidity_sensor
                "wifi_stick" -> R.drawable.ic_wifi_stick
                "wifi_relay" -> R.drawable.ic_wifi_relay
                "camera" -> R.drawable.ic_camera
                else -> R.drawable.ic_not_device

            }
            icon.setImageResource(image)

        }
//        holder.itemView.name_device.text = listDevice[position].name
//        holder.itemView.online_device.text = listDevice[position].online.toString()
    }

    override fun getItemCount(): Int {
        return listDevice.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<Device>){
        listDevice = list
        notifyDataSetChanged()
    }
}