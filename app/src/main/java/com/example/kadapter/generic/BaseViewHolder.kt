package com.example.kadapter.generic

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(val view : View) : RecyclerView.ViewHolder(view) , Binder<T>