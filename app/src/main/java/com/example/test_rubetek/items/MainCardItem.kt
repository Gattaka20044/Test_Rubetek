package com.example.test_rubetek.items

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_rubetek.R
import com.example.test_rubetek.databinding.ItemRoomBinding
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.GroupieAdapter
import com.xwray.groupie.viewbinding.BindableItem

class MainCardItem(
    private val title: String,
    private val items: List<BindableItem<*>>
) : BindableItem<ItemRoomBinding>(){

    override fun bind(viewBinding: ItemRoomBinding, position: Int) {
        viewBinding.apply {
            room.text = title
            //recyclerDevice.layoutManager = GridLayoutManager(,2)
            recyclerDevice.adapter = GroupieAdapter().apply { addAll(items) }
        }
    }

    override fun getLayout() = R.layout.item_room

    override fun initializeViewBinding(view: View): ItemRoomBinding {
        return ItemRoomBinding.bind(view)
    }

}