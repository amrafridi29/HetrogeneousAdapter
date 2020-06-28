package com.example.kadapter.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kadapter.R
import com.example.kadapter.generic.*
import com.example.kadapter.models.HorizontalImageInner
import com.example.kadapter.models.HorizontalImageInnerList
import kotlinx.android.synthetic.main.adapter_inner_recyclerview.view.*

class HorizontalImageInnerListViewBinder() :
        ItemViewBinder<HorizontalImageInnerList , HorizontalImageInnerListViewHolder>(HorizontalImageInnerList::class.java){
    override fun onCreateViewHolder(parent: ViewGroup):BaseViewHolder<HorizontalImageInnerList> {
        return HorizontalImageInnerListViewHolder(parent.getLayout(getItemType()))
    }

    override fun onBindViewHolder(
        model: HorizontalImageInnerList,
        viewHolder: HorizontalImageInnerListViewHolder
    ) {
       viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
        return R.layout.adapter_inner_recyclerview
    }

    override fun areItemsTheSame(
        oldItem: HorizontalImageInnerList,
        newItem: HorizontalImageInnerList
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: HorizontalImageInnerList,
        newItem: HorizontalImageInnerList
    ): Boolean {
        return oldItem==newItem
    }

}

class HorizontalImageInnerListViewHolder(val itemView : View) : BaseViewHolder<HorizontalImageInnerList>(itemView){
  override  fun onBind(model : HorizontalImageInnerList){
        Adapter.Builder()
            .addViewBinder(HorizontalImageInnerViewBinder())
            .submitList(model.images as MutableList<Any>)
            .setLayoutManager( LinearLayoutManager(view.context , RecyclerView.HORIZONTAL , false))
            .into(view.adapter_recycllerview)
            .build()
        view.apply {
            tv_horizontal_header.text = model.title
        }
    }
}