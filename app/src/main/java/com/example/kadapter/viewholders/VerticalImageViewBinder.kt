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
import com.example.kadapter.models.VerticalImage
import kotlinx.android.synthetic.main.adapter_vertical_image.view.*

class VerticalImageViewBinder (val block : (VerticalImage) -> Unit ) :
        ItemViewBinder<VerticalImage , VerticalImageViewHolder>(VerticalImage::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<VerticalImage> {
        return VerticalImageViewHolder(parent.getLayout(getItemType()), block)
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

class VerticalImageViewHolder(val itemView : View, val block: (VerticalImage) -> Unit) : BaseViewHolder<VerticalImage>(itemView){
   override fun onBind(model : VerticalImage){
       view.setOnClickListener {
           block(model)
       }
        Glide.with(view.context)
            .load(model.image)
            .centerCrop()
            .into(view.im_vertical)
    }
}