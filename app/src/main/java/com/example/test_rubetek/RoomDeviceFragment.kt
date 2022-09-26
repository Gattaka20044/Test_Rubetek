package com.example.test_rubetek

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.test_rubetek.databinding.FragmentRoomDeviceBinding
import com.example.test_rubetek.databinding.ItemRoomBinding
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem


class RoomDeviceFragment : Fragment() {

    lateinit var binding: FragmentRoomDeviceBinding


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
        }else{
            Toast.makeText(context, R.string.not_internet, Toast.LENGTH_SHORT).show()
        }

        viewModel.getObserver().observe(viewLifecycleOwner) {
            val items = it.devices.size
            for ( item in  0 until items ) {
                viewModel.insert(viewModel.setDevice(it.devices[item]))
            }
        }

        viewModel.getAllNotes().observe(viewLifecycleOwner){
            val sortRoom = viewModel.sortRoom(it)
            val devices = mutableListOf<BindableItem<ItemRoomBinding>>()
            for (item in sortRoom.indices){
                val sortDevice = viewModel.sortDevice(it,it,sortRoom[item])
                devices.add(item,viewModel.getDevice(sortDevice, sortRoom[item]))
            }


            binding.rvRoom.adapter = GroupieAdapter().apply { addAll(devices) }
        }
    }

}