package com.example.kadapter.generic

import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class ItemViewBinder<M ,in VH : BaseViewHolder<M>>(val modelClass : Class<out M>)  : DiffUtil.ItemCallback<M>(){
    abstract fun onCreateViewHolder(parent: ViewGroup ) : RecyclerView.ViewHolder
    abstract fun onBindViewHolder(model : M , viewHolder : VH)
    abstract fun getItemType() : Int


    open fun onViewRecycled(viewHolder: VH) = Unit
    open fun onViewDetachedFromWindow(viewHolder: VH) = Unit

}