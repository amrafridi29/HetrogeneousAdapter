package com.example.kadapter.generic

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

typealias ItemClass = Class<out Any>
typealias ItemBinder = ItemViewBinder<Any , BaseViewHolder<Any>>

class GenericAdapter (private val viewBinders : Map<ItemClass , ItemBinder>)
    : ListAdapter<Any , BaseViewHolder<Any>>(GenericDiffUtilCallback(viewBinders)){



    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getItemType() }

    private fun getViewBinders(viewType : Int) : ItemBinder = viewTypeToBinders.getValue(viewType)

    override fun getItemViewType(position: Int): Int {
        return viewBinders.getValue(getItem(position).javaClass).getItemType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Any> {
        return getViewBinders(viewType).onCreateViewHolder(parent)
    }


    override fun onBindViewHolder(holder:BaseViewHolder<Any>, position: Int) {
        getViewBinders(getItemViewType(position)).onBindViewHolder(getItem(position) , holder)
    }

    override fun onViewRecycled(holder: BaseViewHolder<Any>) {
        getViewBinders(holder.itemViewType).onViewRecycled(holder)
        super.onViewRecycled(holder)
    }

    override fun onViewDetachedFromWindow(holder: BaseViewHolder<Any>) {
        getViewBinders(holder.itemViewType).onViewDetachedFromWindow(holder)
        super.onViewDetachedFromWindow(holder)
    }



}