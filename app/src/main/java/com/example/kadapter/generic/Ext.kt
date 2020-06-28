package com.example.kadapter.generic

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.getLayout(@LayoutRes resId : Int , attachToRoot : Boolean = false): View {
    return LayoutInflater.from(context).inflate(resId , this , attachToRoot)
}