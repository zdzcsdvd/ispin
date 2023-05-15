package com.bw.ispin.example.mymvi.entity

data class ResponseData<T>(
    val code:Int,
    val msg:String,
    val data:T?
)