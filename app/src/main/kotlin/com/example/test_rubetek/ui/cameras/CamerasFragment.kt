package com.example.test_rubetek.ui.cameras

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_rubetek.R

class CamerasFragment : Fragment() {

    companion object {
        fun newInstance() = CamerasFragment()
    }

    private lateinit var viewModel: CamerasViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cameras, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CamerasViewModel::class.java)
        // TODO: Use the ViewModel
    }

}