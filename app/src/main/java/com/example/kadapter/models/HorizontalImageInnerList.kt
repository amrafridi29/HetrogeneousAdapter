package com.example.kadapter.models

data class HorizontalImageInnerList(
    val images : List<HorizontalImageInner>,
    val type : Int = Constants.HORIZONTAL_INNER_LIST,
    val id : Int  = Constants.HORIZONTAL_INNER_LIST,
    val title : String = ""
)