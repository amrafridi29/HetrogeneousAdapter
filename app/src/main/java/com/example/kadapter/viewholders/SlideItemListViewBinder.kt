package com.example.kadapter.viewholders

import android.view.View
import android.view.ViewGroup
import com.example.kadapter.R
import com.example.kadapter.generic.BaseViewHolder
import com.example.kadapter.generic.ItemViewBinder
import com.example.kadapter.generic.getLayout
import com.example.kadapter.models.SlideItemList
import kotlinx.android.synthetic.main.adapter_slide_item.view.*

class SlideItemListViewBinder () :
        ItemViewBinder<SlideItemList , SlideItemListViewHolder>(SlideItemList::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<SlideItemList> {
        return SlideItemListViewHolder(parent.getLayout(getItemType()))
    }

    override fun onBindViewHolder(model: SlideItemList, viewHolder: SlideItemListViewHolder) {
        viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
       return R.layout.adapter_slide_item
    }

    override fun areItemsTheSame(oldItem: SlideItemList, newItem: SlideItemList): Boolean {
        return oldItem.slideItems == newItem.slideItems
    }

    override fun areContentsTheSame(oldItem: SlideItemList, newItem: SlideItemList): Boolean {
        return oldItem ==newItem
    }

}
class SlideItemListViewHolder(val itemView : View) : BaseViewHolder<SlideItemList>(itemView){
    override fun onBind(model: SlideItemList) {
        view.imageSlider.setSliderAdapter(SlideImageAdapter(model.slideItems));
    }

}