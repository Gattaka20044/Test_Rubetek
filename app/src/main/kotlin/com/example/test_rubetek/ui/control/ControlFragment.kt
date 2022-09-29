package com.example.test_rubetek.ui.control

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test_rubetek.R

class ControlFragment : Fragment() {

    companion object {
        fun newInstance() = ControlFragment()
    }

    private lateinit var viewModel: ControlViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_control, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ControlViewModel::class.java)
        // TODO: Use the ViewModel
    }

}