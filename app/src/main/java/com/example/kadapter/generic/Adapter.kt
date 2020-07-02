package com.example.kadapter.generic

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Adapter constructor(builder : Builder) {
    private val viewBinders : MutableMap<ItemClass , ItemBinder>
    private lateinit var listData : MutableList<Any>
    private var mRecyclerView : RecyclerView?
    private var mLayoutManager : RecyclerView.LayoutManager?
    private var adapter : GenericAdapter? = null



    init {
        viewBinders= builder.viewBinders
        listData = builder.listData
        mRecyclerView = builder.mRecyclerView
        mLayoutManager = builder.mLayoutManager

         adapter =  GenericAdapter(viewBinders)
        adapter?.submitList(listData as List<Any>?)
        mRecyclerView?.layoutManager = mLayoutManager
        mRecyclerView?.adapter = adapter
    }

     class Builder(){
         internal val viewBinders = mutableMapOf<ItemClass , ItemBinder>()
         internal var listData = mutableListOf<Any>()
         internal var mRecyclerView : RecyclerView? = null
         internal var mLayoutManager : RecyclerView.LayoutManager? = null
        fun addViewBinder(viewBinder : ItemViewBinder<*, *>) = apply {
            viewBinders.put(viewBinder.modelClass , viewBinder as ItemBinder)
        }

        fun submitList(data : MutableList<Any>) = apply { listData = data }
         fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) = apply { mLayoutManager = layoutManager }
        fun into(recyclerView: RecyclerView) = apply { mRecyclerView = recyclerView }

        fun build() : Adapter{
            return Adapter(this)
        }


    }


}