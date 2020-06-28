package com.example.kadapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadapter.R
import com.example.kadapter.generic.GenericAdapter
import com.example.kadapter.generic.ItemBinder
import com.example.kadapter.generic.ItemClass
import com.example.kadapter.generic.ItemViewBinder
import com.example.kadapter.models.HorizontalImage
import com.example.kadapter.models.HorizontalImageList
import kotlinx.android.synthetic.main.adapter_recycleriew.view.*

class HorizontalImageListViewBinder () :
        ItemViewBinder<HorizontalImageList , HorizontalImageListViewHolder>(HorizontalImageList::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HorizontalImageListViewHolder(LayoutInflater.from(parent.context)
            .inflate(getItemType() , parent, false))
    }

    override fun onBindViewHolder(
        model: HorizontalImageList,
        viewHolder: HorizontalImageListViewHolder
    ) {
       viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
        return R.layout.adapter_recycleriew
    }

    override fun areItemsTheSame(
        oldItem: HorizontalImageList,
        newItem: HorizontalImageList
    ): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(
        oldItem: HorizontalImageList,
        newItem: HorizontalImageList
    ): Boolean {
        return newItem ==oldItem
    }

}

class HorizontalImageListViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    fun onBind(model : HorizontalImageList){
        val horizontalImageViewBinder = HorizontalImageViewBinder()
        val viewBinders = mutableMapOf<ItemClass , ItemBinder>()
        viewBinders.put(horizontalImageViewBinder.modelClass,horizontalImageViewBinder  as ItemBinder)
        val adapter = GenericAdapter(viewBinders)
        view.apply {
            tv_horizontal_header.text = model.title
            adapter_recycllerview.layoutManager = LinearLayoutManager(context , RecyclerView.HORIZONTAL , false)
            adapter_recycllerview.adapter = adapter
            adapter.submitList(model.images)
        }
    }
}