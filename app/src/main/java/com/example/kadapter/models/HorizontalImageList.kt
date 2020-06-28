package com.example.kadapter.models

data class HorizontalImageList(
    val images : List<HorizontalImage>,
    val id : Int = Constants.HORIZONTAL_LIST,
    val type : Int = Constants.HORIZONTAL_LIST,
    val title : String =""
)