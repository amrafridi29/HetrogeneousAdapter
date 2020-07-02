package com.example.kadapter.viewholders

import android.content.Context
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kadapter.R
import com.example.kadapter.generic.*
import com.example.kadapter.models.VerticalImage
import kotlinx.android.synthetic.main.adapter_vertical_image.view.*

class VerticalImageViewBinder (val context : Context , val block : (VerticalImage) -> Unit  ) :
        ActionModeItemViewBinder<VerticalImage , VerticalImageViewBinder.VerticalImageViewHolder>(VerticalImage::class.java , context) {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder<VerticalImage> {
        return VerticalImageViewHolder(parent.getLayout(getItemType()), block)
    }

    override fun onBindViewHolder(model: VerticalImage, viewHolder: VerticalImageViewHolder) {
        viewHolder.onBind(model)
    }

    override fun getItemType(): Int {
        return R.layout.adapter_vertical_image
    }

    fun selectAll(){
        adapter.currentList.forEachIndexed { index, obj ->
            if(obj is VerticalImage)
                selectItem(index)
        }
        adapter
    }


    override fun onCabItemClicked(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_delete -> {
                Toast.makeText(context, "Delete Item adapter", Toast.LENGTH_SHORT)
                    .show()
                finish()
            }
            R.id.menu_all -> selectAll()
            R.id.menu_show -> getSelectedItems()
        }
        return true
    }


    override fun areItemsTheSame(oldItem: VerticalImage, newItem: VerticalImage): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: VerticalImage, newItem: VerticalImage): Boolean {
        return newItem == oldItem
    }

    override fun getMenuLayout(): Int {
        return R.menu.select_menu
    }


    inner class VerticalImageViewHolder(val itemView: View, val block: (VerticalImage) -> Unit) :
        BaseViewHolder<VerticalImage>(itemView) {
        override fun onBind(model: VerticalImage) {

            view.setOnClickListener {
                start()
                toggleSelection(adapterPosition)
                block(model)
            }
            Glide.with(view.context)
                .load(model.image)
                .centerCrop()
                .into(view.im_vertical)
        }
    }




}