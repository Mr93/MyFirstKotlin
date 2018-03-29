package com.example.prora.myfirstkotlin.api

class RedditNewsResponse(val data: RedditDataResponse)

class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)

class RedditChildrenResponse(val data: RedditNewsData)

class RedditNewsData(
        val author: String,
        val title: String,
        val numberComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)

