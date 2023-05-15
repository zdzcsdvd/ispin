package com.bw.ispin.example.mymvi.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object GlideUtils {

    @JvmStatic
    @BindingAdapter("android:url")
    fun setImage(iv:ImageView,url:String){
        Glide.with(iv).load(url).into(iv)
    }
}