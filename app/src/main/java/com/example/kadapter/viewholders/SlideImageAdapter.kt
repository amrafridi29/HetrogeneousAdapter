package com.example.kadapter.viewholders

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.kadapter.R
import com.example.kadapter.generic.getLayout
import com.example.kadapter.models.SlideItem
import com.smarteist.autoimageslider.SliderViewAdapter
import kotlinx.android.synthetic.main.item_slide.view.*

class SlideImageAdapter(private val list : MutableList<SlideItem>) : SliderViewAdapter<SlideImageAdapter.SliderAdapterVH>(){

    inner class SliderAdapterVH(private val view : View?) : SliderViewAdapter.ViewHolder(view){
       fun onBind(model : SlideItem){
           itemView?.apply {
               Glide.with(context)
                   .load(model.image)
                   .centerCrop()
                   .into(im_slide)
           }
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderAdapterVH {
        return SliderAdapterVH(parent?.getLayout(R.layout.item_slide))
    }

    override fun getCount()= list.size

    override fun onBindViewHolder(viewHolder: SliderAdapterVH?, position: Int) {
        viewHolder?.onBind(list[position])
    }

}