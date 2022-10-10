package com.example.test_rubetek.ui.roomdevice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.test_rubetek.App
import com.example.test_rubetek.R
import com.example.test_rubetek.databinding.FragmentRoomDeviceBinding
import com.example.test_rubetek.databinding.ItemRoomBinding
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import org.koin.android.viewmodel.ext.android.viewModel


class RoomDeviceFragment : Fragment() {
    private val viewModel : RoomDeviceViewModel by viewModel()
    private var _binding: FragmentRoomDeviceBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomDeviceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        viewModel.getResponse()
        lifecycleScope.launchWhenStarted {
            viewModel.roomDeviceStateFlow.onEach {
                val items = it.devices.size
                //Log.d("TAG", "$items")
                for ( item in  0 until items ) {
                    viewModel.insert(viewModel.setDevice(it.devices[item]))
                    //Log.d("TAG", "${it.devices[item].room}")
                }

            }.collect()
        }
//        lifecycleScope.launchWhenStarted {
//            viewModel.response.onEach {
//                Log.d("tag", "$it")
//                val sortRoom = viewModel.sortRoom(it)
//                val devices = mutableListOf<BindableItem<ItemRoomBinding>>()
//                for (item in sortRoom.indices){
//                    val sortDevice = viewModel.sortDevice(it,it,sortRoom[item])
//                    val getDevice = viewModel.getDevice(sortDevice, sortRoom[item])
//                    devices.add(item,getDevice)
//                }
////
//                binding.rvRoom.adapter = GroupieAdapter().apply { addAll(devices) }
//            }.collect()
//            binding.rvRoom.adapter = GroupieAdapter().apply { addAll(devices) }
//        }
        viewModel.getAllDevice().observe(viewLifecycleOwner){
            val sortRoom = viewModel.sortRoom(it)
            val devices = mutableListOf<BindableItem<ItemRoomBinding>>()
            for (item in sortRoom.indices){
                val sortDevice = viewModel.sortDevice(it,it,sortRoom[item])
                val getDevice = viewModel.getDevice(sortDevice, sortRoom[item])
                devices.add(item,getDevice)
            }

            binding.rvRoom.adapter = GroupieAdapter().apply { addAll(devices) }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
            _binding = null
    }

}