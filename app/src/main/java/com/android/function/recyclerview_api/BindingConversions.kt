package com.android.function.recyclerview_api

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingConversions {

    @BindingAdapter("imageUrl","error")
    @JvmStatic
    fun loadImage(imageView : ImageView, url : String, error : Drawable){
        Glide.with(imageView.context).load(url)
            .error(error)
            .into(imageView)
    }

    // 속성 로직도 변경 가능
//    @BindingAdapter("android:paddingLeft")
//    fun setPaddingLeft(view: View, padding: Int) {
//        view.setPadding(padding,
//            view.getPaddingTop(),
//            view.getPaddingRight(),
//            view.getPaddingBottom())
//    }

}