@file:JvmName("ExtensionsUtils")

package com.example.prora.myfirstkotlin.commons.extentions

import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.prora.myfirstkotlin.R

fun ViewGroup.inflate(layoutId: Int, attackToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attackToRoot)
}

fun ImageView.loadImg(url: String) {
    if (TextUtils.isEmpty(url) || !url.contains("https")) {
        Glide.with(context).load(R.drawable.ic_launcher_background).into(this)
    } else {
        Glide.with(context).load(url).into(this)
    }
}

//fun <T:RecyclerView.ViewHolder> T.listen(event:(position : Int, type: Int)-> Unit) : T{
//    itemView.setOnClickListener {
//        event.invoke(adapterPosition, itemViewType)
//    }
//    return this
//}