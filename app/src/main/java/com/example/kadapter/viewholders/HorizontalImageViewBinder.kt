package com.example.kadapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kadapter.R
import com.example.kadapter.generic.BaseViewHolder
import com.example.kadapter.generic.ItemViewBinder
import com.example.kadapter.generic.getLayout
import com.example.kadapter.models.HorizontalImage
import kotlinx.android.synthetic.main.adapter_horizontal_image.view.*

class HorizontalImageViewBinder() :
        ItemViewBinder<HorizontalImage , HorizontalImageViewHolder>(HorizontalImage::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HorizontalImage> {
        return HorizontalImageViewHolder(parent.getLayout(getItemType()))
    }

    override fun onBindViewHolder(model: HorizontalImage, viewHolder: HorizontalImageViewHolder) {
        viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
       return R.layout.adapter_horizontal_image
    }

    override fun areItemsTheSame(oldItem: HorizontalImage, newItem: HorizontalImage): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: HorizontalImage, newItem: HorizontalImage): Boolean {
        return oldItem ==newItem
    }

}

class HorizontalImageViewHolder(val itemView : View) :BaseViewHolder<HorizontalImage>(itemView){
   override fun onBind(model : HorizontalImage){
        Glide.with(view.context)
            .load(model.image)
            .centerCrop()
            .into(view.im_horizontal)
    }
}