package com.example.kadapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kadapter.R
import com.example.kadapter.generic.ItemViewBinder
import com.example.kadapter.models.VerticalImage
import kotlinx.android.synthetic.main.adapter_vertical_image.view.*

class VerticalImageViewBinder () :
        ItemViewBinder<VerticalImage , VerticalImageViewHolder>(VerticalImage::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return VerticalImageViewHolder(LayoutInflater.from(parent.context)
            .inflate(getItemType() , parent , false))
    }

    override fun onBindViewHolder(model: VerticalImage, viewHolder: VerticalImageViewHolder) {
       viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
       return R.layout.adapter_vertical_image
    }

    override fun areItemsTheSame(oldItem: VerticalImage, newItem: VerticalImage): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: VerticalImage, newItem: VerticalImage): Boolean {
        return newItem ==oldItem
    }

}

class VerticalImageViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    fun onBind(model : VerticalImage){
        Glide.with(view.context)
            .load(model.image)
            .centerCrop()
            .into(view.im_vertical)
    }
}