package com.example.kadapter.generic

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

typealias ItemClass = Class<out Any>
typealias ItemBinder = ItemViewBinder<Any , RecyclerView.ViewHolder>

class GenericAdapter (private val viewBinders : Map<ItemClass , ItemBinder>)
    : ListAdapter<Any , RecyclerView.ViewHolder>(GenericDiffUtilCallback(viewBinders)){



    private val viewTypeToBinders = viewBinders.mapKeys { it.value.getItemType() }

    private fun getViewBinders(viewType : Int) : ItemBinder = viewTypeToBinders.getValue(viewType)

    override fun getItemViewType(position: Int): Int {
        return viewBinders.getValue(getItem(position).javaClass).getItemType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewBinders(viewType).onCreateViewHolder(parent)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getViewBinders(getItemViewType(position)).onBindViewHolder(getItem(position) , holder)
    }



}