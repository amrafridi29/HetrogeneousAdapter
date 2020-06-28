package com.example.kadapter.generic

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView


internal class GenericDiffUtilCallback (
    private val viewBinders : Map<ItemClass, ItemBinder>
) : DiffUtil.ItemCallback<Any>(){
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if(oldItem::class !=newItem::class)
            return false
        return viewBinders[oldItem::class.java]?.areItemsTheSame(oldItem , newItem) ?: false
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return viewBinders[oldItem::class.java]?.areContentsTheSame(oldItem , newItem) ?: false
    }

}