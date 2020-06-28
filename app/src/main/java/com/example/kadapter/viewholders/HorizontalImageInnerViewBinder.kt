package com.example.kadapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kadapter.R
import com.example.kadapter.generic.ItemViewBinder
import com.example.kadapter.models.HorizontalImageInner
import kotlinx.android.synthetic.main.adapter_inner_veritcal_image.view.*

class HorizontalImageInnerViewBinder () :
        ItemViewBinder<HorizontalImageInner , HorizontalImageInnerViewHolder>(HorizontalImageInner::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return HorizontalImageInnerViewHolder(LayoutInflater.from(parent.context)
            .inflate(getItemType() , parent , false))
    }

    override fun onBindViewHolder(
        model: HorizontalImageInner,
        viewHolder: HorizontalImageInnerViewHolder
    ) {
        viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
        return R.layout.adapter_inner_veritcal_image
    }

    override fun areItemsTheSame(
        oldItem: HorizontalImageInner,
        newItem: HorizontalImageInner
    ): Boolean {
        return oldItem.image==newItem.image
    }

    override fun areContentsTheSame(
        oldItem: HorizontalImageInner,
        newItem: HorizontalImageInner
    ): Boolean {
        return oldItem == newItem
    }

}

class HorizontalImageInnerViewHolder(val view : View) : RecyclerView.ViewHolder(view){
    fun onBind(model : HorizontalImageInner){
        Glide.with(view.context)
            .load(model.image)
            .centerCrop()
            .into(view.im_vertical_inner)
    }
}