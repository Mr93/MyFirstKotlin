@file:JvmName("ExtensionsUtils")

package com.example.prora.myfirstkotlin.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attackToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attackToRoot)
}