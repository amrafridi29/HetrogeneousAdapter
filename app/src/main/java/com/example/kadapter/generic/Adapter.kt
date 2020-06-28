package com.example.kadapter.generic

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

object Adapter {
    private var adapter : GenericAdapter? = null
    private val viewBinders = mutableMapOf<ItemClass , ItemBinder>()
    private var listData = mutableListOf<Any>()
    private var mRecyclerView : RecyclerView? = null
    private var mLayoutManager : RecyclerView.LayoutManager? = null

     class Builder(){
        fun addViewBinder(viewBinder : ItemViewBinder<*, *>) = apply {
            viewBinders.put(viewBinder.modelClass , viewBinder as ItemBinder)
        }

        fun submitList(data : MutableList<Any>) = apply { listData = data }
         fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) = apply { mLayoutManager = layoutManager }
        fun into(recyclerView: RecyclerView) = apply { mRecyclerView = recyclerView }

        fun build() : GenericAdapter?{
            adapter =  GenericAdapter(viewBinders)
            adapter?.submitList(listData as List<Any>?)
            mRecyclerView?.layoutManager = mLayoutManager
            mRecyclerView?.adapter = adapter
            return adapter
        }


    }


}