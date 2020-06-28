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
import com.example.kadapter.models.HorizontalImageInner
import com.example.kadapter.models.HorizontalImageInnerList
import kotlinx.android.synthetic.main.adapter_inner_recyclerview.view.*

class HorizontalImageInnerListViewBinder() :
        ItemViewBinder<HorizontalImageInnerList , HorizontalImageInnerListViewHolder>(HorizontalImageInnerList::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HorizontalImageInnerListViewHolder(LayoutInflater.from(parent.context).
        inflate(getItemType() , parent , false))
    }

    override fun onBindViewHolder(
        model: HorizontalImageInnerList,
        viewHolder: HorizontalImageInnerListViewHolder
    ) {
       viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
        return R.layout.adapter_inner_recyclerview
    }

    override fun areItemsTheSame(
        oldItem: HorizontalImageInnerList,
        newItem: HorizontalImageInnerList
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: HorizontalImageInnerList,
        newItem: HorizontalImageInnerList
    ): Boolean {
        return oldItem==newItem
    }

}

class HorizontalImageInnerListViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    fun onBind(model : HorizontalImageInnerList){
        val horizontalImageInnerViewBinder = HorizontalImageInnerViewBinder()
        val viewBinders= mutableMapOf<ItemClass , ItemBinder>()
        viewBinders.put(horizontalImageInnerViewBinder.modelClass,
        horizontalImageInnerViewBinder as ItemBinder)
        val adapter = GenericAdapter(viewBinders)
        view.apply {
            tv_horizontal_header.text = model.title
            adapter_recycllerview.layoutManager= LinearLayoutManager(context , RecyclerView.HORIZONTAL , false)
            adapter_recycllerview.adapter = adapter
            adapter.submitList(model.images)
        }
    }
}