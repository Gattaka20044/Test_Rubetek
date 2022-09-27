package com.example.test_rubetek.items

import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_rubetek.R
import com.example.test_rubetek.databinding.ItemRoomBinding
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem

class MainCardItem(
    private val title: String,
    //private val visibility: String,
    private val items: List<BindableItem<*>>
) : BindableItem<ItemRoomBinding>(){

    override fun bind(viewBinding: ItemRoomBinding, position: Int) {
        viewBinding.apply {
            room.text = title
            recyclerDevice.adapter = GroupieAdapter().apply { addAll(items) }
            rvItem.setOnClickListener {
                if (recyclerDevice.visibility == GONE){
                    recyclerDevice.visibility = VISIBLE
                    imageExpand.setImageResource(R.drawable.ic_baseline_expand_less_24)
                }else{
                    recyclerDevice.visibility = GONE
                    imageExpand.setImageResource(R.drawable.ic_baseline_expand_more_24)
                }
            }
        }
    }

    override fun getLayout() = R.layout.item_room

    override fun initializeViewBinding(view: View): ItemRoomBinding {
        return ItemRoomBinding.bind(view)
    }
}