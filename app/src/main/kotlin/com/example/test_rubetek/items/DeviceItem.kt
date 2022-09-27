package com.example.test_rubetek.items

import android.graphics.Color
import android.view.View
import com.example.test_rubetek.Device
import com.example.test_rubetek.R
import com.example.test_rubetek.databinding.ItemDeviceBinding
import com.xwray.groupie.viewbinding.BindableItem

class DeviceItem(private val content: Device) : BindableItem<ItemDeviceBinding>() {

    override fun bind(viewBinding: ItemDeviceBinding, position: Int) {
        viewBinding.apply {
            nameDevice.text = content.name
            if (content.online){
                onlineDevice.setText(R.string.online)
                onlineDevice.setTextColor(Color.GREEN)
            }else{
                onlineDevice.setText(R.string.offline)
                onlineDevice.setTextColor(Color.RED)
            }
            val image = when (content.icon) {
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

    }

    override fun getLayout(): Int = R.layout.item_device

    override fun initializeViewBinding(view: View): ItemDeviceBinding {
        return ItemDeviceBinding.bind(view)
    }

}