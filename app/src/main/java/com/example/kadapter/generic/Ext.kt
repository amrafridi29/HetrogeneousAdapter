package com.example.kadapter.generic

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import java.lang.reflect.Field

fun ViewGroup.getLayout(@LayoutRes resId : Int , attachToRoot : Boolean = false): View {
    return LayoutInflater.from(context).inflate(resId , this , attachToRoot)
}

fun ActionMode?.setActionCloseDrawable(
    activity : AppCompatActivity,
    drawableResId: Int
): Boolean {
    val TAG = "Action mode"
    if (this != null) {
        // Change the drawable for the close ImageView to the desired icon
        // Unfortunately, this can only be done with reflection
        try {
            val wrappedObjectField: Field = this.javaClass.getDeclaredField("mWrappedObject")
            wrappedObjectField.isAccessible = true
            val mWrappedObject: Any = wrappedObjectField.get(this)
            val contextViewField: Field = mWrappedObject.javaClass.getDeclaredField("mContextView")
            contextViewField.isAccessible = true
            val mContextView: Any = contextViewField.get(mWrappedObject)
            val closeField: Field = mContextView.javaClass.getDeclaredField("mClose")
            closeField.isAccessible = true
            val mClose: Any = closeField.get(mContextView)
            if (mClose is ImageView) {
                (mClose as ImageView).setImageDrawable(ContextCompat.getDrawable(activity,drawableResId))
                return true
            }
        } catch (ex: Exception) {
            Log.e(
                TAG,
                """${ex.javaClass.simpleName} in #setActionCloseDrawable: ${ex.localizedMessage}
                    ${Log.getStackTraceString(ex)}"""
            )
        }
    }
    return false
}

inline fun <reified T> ListAdapter<* , *>.getfilteredList() : MutableList<T>{
    val list = mutableListOf<T>()
    currentList.filterIsInstance<T>().forEach {
       list.add(it)
    }
   return list
}