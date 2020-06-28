package com.example.kadapter.generic

interface Binder<T> {
    fun onBind(model : T)
}