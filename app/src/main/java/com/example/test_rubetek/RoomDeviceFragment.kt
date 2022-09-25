package com.example.test_rubetek

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test_rubetek.databinding.FragmentRoomDeviceBinding

class RoomDeviceFragment : Fragment() {

    lateinit var binding: FragmentRoomDeviceBinding
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: DeviceAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRoomDeviceBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        val viewModel = ViewModelProvider(this).get(RoomDeviceViewModel::class.java)
        viewModel.initDatabase()
        if (context?.let { viewModel.internetCheck(it) } == true){
            viewModel.getResponse()
        }


        viewModel.getObserver().observe(viewLifecycleOwner) {
            Log.d("TAG", "${it.devices.size}")
            Log.d("Sort", "${viewModel.sort(it)}")

            val items = it.devices.size
        }

        viewModel.getObserver().observe(viewLifecycleOwner) {
            Log.d("TAG", "${it.devices.size}")
            val items = it.devices.size

            for ( item in  0 until items ) {
                viewModel.insert(Device(
                    id = it.devices[item].id,
                    `class` = it.devices[item].`class`,
                    icon = it.devices[item].icon,
                    name = it.devices[item].name,
                    room = it.devices[item].room,
                    hidden = it.devices[item].hidden,
                    favorite = it.devices[item].favorite,
                    disabled = it.devices[item].disabled,
                    online = it.devices[item].online,
                ))
            }
        }

        recyclerView = binding.rvNotes
        adapter = DeviceAdapter()
        recyclerView.adapter = adapter

        viewModel.getAllNotes().observe(viewLifecycleOwner){
            adapter.setList(it)
        }
    }

}