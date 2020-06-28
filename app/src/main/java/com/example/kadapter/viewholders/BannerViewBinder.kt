package com.example.kadapter.viewholders

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.kadapter.R
import com.example.kadapter.generic.BaseViewHolder
import com.example.kadapter.generic.ItemViewBinder
import com.example.kadapter.generic.getLayout
import com.example.kadapter.models.BannerAd
import kotlinx.android.synthetic.main.adapter_banner.view.*

class BannerViewBinder () :
        ItemViewBinder<BannerAd , BannerViewHolder>(BannerAd::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<BannerAd> {
        return BannerViewHolder(parent.getLayout(getItemType()))
    }

    override fun onBindViewHolder(model: BannerAd, viewHolder: BannerViewHolder) {
       viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
       return R.layout.adapter_banner
    }

    override fun areItemsTheSame(oldItem: BannerAd, newItem: BannerAd): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: BannerAd, newItem: BannerAd): Boolean {
       return newItem==oldItem
    }

}

class BannerViewHolder(val itemView : View) : BaseViewHolder<BannerAd>(itemView) {
    override fun onBind(model: BannerAd) {
        view.apply {
            Glide.with(context)
                .load(model.image)
                .centerCrop()
                .into(im_banner)
            tv_banner_title.text= model.title
            tv_banner_descp.text = model.description
        }
    }

}