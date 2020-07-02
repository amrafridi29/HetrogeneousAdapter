package com.example.kadapter.generic

import android.content.Context
import android.util.SparseBooleanArray
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.core.util.forEach
import androidx.core.util.isEmpty
import com.example.kadapter.models.VerticalImage


abstract class ActionModeItemViewBinder<M : Any ,in VH : BaseViewHolder<M >>(val mModelClass : Class<out M>, private val context : Context ) : ItemViewBinder<M , VH>(mModelClass) , CabCallback {
    private val selectedItems = SparseBooleanArray()
    private var cabAction : ContextualActionBar?=null
    abstract fun getMenuLayout() : Int

    fun start() {
        cabAction?.start()
    }

    fun finish(){
        cabAction?.finish()
    }

    fun setCabTitle(title : String){
        cabAction?.setTitle(title)
    }

    init {
        cabAction= ContextualActionBar.Builder()
            .setMenu(getMenuLayout())
            .with(context as? AppCompatActivity)
            .setTitle("Select Items")
            .setCabCallback(this)
            .build()
    }

    fun toggleSelection(position: Int) : Int{
        if (selectedItems.get(position, false)) {
            selectedItems.delete(position)
        } else {
            selectedItems.put(position, true)
        }
        setCabTitle("Selected Items (${selectedItems.size()})")
        if(selectedItems.isEmpty())
            finish()
        return position
    }

    fun clearSelection() {
        selectedItems.clear()
    }

    fun selectItem(position: Int){
        if(!selectedItems.get(position, false)) {
            selectedItems.put(position, true)
            setCabTitle("Selected Items (${selectedItems.size()})")
        }
    }

    fun getSelectedItems() : MutableList<Any>{
        val list = mutableListOf<Any>()
        selectedItems.forEach { key, value ->
            list.add(adapter.currentList.get(key))
        }
        return list
    }



     override fun onCreateCab(cab: ContextualActionBar?, menu: Menu?): Boolean {
        return true
    }

    override fun onDestroyCab(cab: ContextualActionBar?): Boolean {
        clearSelection()
        return true
    }








}
