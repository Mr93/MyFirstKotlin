package com.example.prora.myfirstkotlin.features.news

import com.example.prora.myfirstkotlin.commons.RedditNewsItem
import rx.Observable
import rx.observers.Observers

class NewsManager() {
    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create { subscriber ->
            val news = mutableListOf<RedditNewsItem>()
            for (i in 1..10) {
                news.add(RedditNewsItem(
                        "author $i",
                        "Title $i",
                        i,
                        1457207701L - i * 200,
                        "https://picsum.photos/200/200?image=$i",
                        "url"
                ))
            }
            subscriber.onNext(news)
        }
    }
}