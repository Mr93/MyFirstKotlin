package com.example.prora.myfirstkotlin.commons

import com.example.prora.myfirstkotlin.adapaters.AdapterConstant
import com.example.prora.myfirstkotlin.adapaters.ViewType

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
) : ViewType {
    override fun getViewType() = AdapterConstant.NEWS
}
