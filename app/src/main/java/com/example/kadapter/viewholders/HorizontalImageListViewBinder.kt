package com.example.kadapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadapter.R
import com.example.kadapter.generic.*
import com.example.kadapter.models.HorizontalImage
import com.example.kadapter.models.HorizontalImageList
import kotlinx.android.synthetic.main.adapter_recycleriew.view.*

class HorizontalImageListViewBinder () :
        ItemViewBinder<HorizontalImageList , HorizontalImageListViewHolder>(HorizontalImageList::class.java){
    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<HorizontalImageList> {
        return HorizontalImageListViewHolder(parent.getLayout(getItemType()))
    }

    override fun onBindViewHolder(
        model: HorizontalImageList,
        viewHolder: HorizontalImageListViewHolder
    ) {
       viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
        return R.layout.adapter_recycleriew
    }

    override fun areItemsTheSame(
        oldItem: HorizontalImageList,
        newItem: HorizontalImageList
    ): Boolean {
        return oldItem.id == newItem.id

    }

    override fun areContentsTheSame(
        oldItem: HorizontalImageList,
        newItem: HorizontalImageList
    ): Boolean {
        return newItem ==oldItem
    }

}

class HorizontalImageListViewHolder(val itemView : View) :BaseViewHolder<HorizontalImageList>(itemView){
   override fun onBind(model : HorizontalImageList){

        view.apply {
            Adapter.Builder()
                .addViewBinder(HorizontalImageViewBinder())
                .setLayoutManager(LinearLayoutManager(context , RecyclerView.HORIZONTAL , false))
                .into(adapter_recycllerview)
                .submitList(model.images as MutableList<Any>)
                .build()
            tv_horizontal_header.text = model.title

        }
    }
}