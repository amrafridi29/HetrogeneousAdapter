package com.example.kadapter.generic

import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewBinder<M ,in VH : RecyclerView.ViewHolder>(val modelClass : Class<out M>)  : DiffUtil.ItemCallback<M>(){
    abstract fun onCreateViewHolder(parent: ViewGroup ) : RecyclerView.ViewHolder
    abstract fun onBindViewHolder(model : M , viewHolder : VH)
    abstract fun getItemType() : Int
}