package com.example.prora.myfirstkotlin.fragments

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prora.myfirstkotlin.R
import com.example.prora.myfirstkotlin.adapaters.NewsAdapter
import com.example.prora.myfirstkotlin.commons.InfinityScrollListener
import com.example.prora.myfirstkotlin.commons.RedditNews
import com.example.prora.myfirstkotlin.commons.RxBaseFragment
import com.example.prora.myfirstkotlin.commons.extentions.inflate
import com.example.prora.myfirstkotlin.features.news.NewsManager
import kotlinx.android.synthetic.main.news_fragment.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import rx.subscriptions.CompositeSubscription


class NewsFragment : RxBaseFragment() {

    val TAG = "NewsFragment"

    companion object {
        private val KEY_REDDIT_NEWS = "KEY_REDDIT_NEWS"
    }

    private var redditNews: RedditNews? = null

    private var infinityScrollListener: InfinityScrollListener? = null

    private val newsManager by lazy { NewsManager() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        news_list.apply {
            news_list.setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(context)
            news_list.layoutManager = linearLayout
            news_list.clearOnScrollListeners()
            infinityScrollListener = InfinityScrollListener({ requestNews() }, linearLayout)
            news_list.addOnScrollListener(infinityScrollListener)
            swipe_refresh.setOnRefreshListener { reLoadNews() }
            swipe_refresh.setColorSchemeResources(R.color.colorPrimary, android.R.color.holo_green_dark, android.R.color
                    .holo_orange_dark, android.R.color.holo_blue_dark)
        }
        initAdapter()
        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (news_list.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (news_list.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.isNotEmpty()) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun reLoadNews() {
        clearSubScription()
        subscriptions = CompositeSubscription()
        val subscription = newsManager.getNews("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            redditNews = retrievedNews
                            (news_list.adapter as NewsAdapter).clearAndAddNews(retrievedNews.news)
                            swipe_refresh.isRefreshing = false
                            infinityScrollListener?.resetPreviousTotal()
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                            swipe_refresh.isRefreshing = false
                        }
                )
        subscriptions.add(subscription)
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedNews ->
                            redditNews = retrievedNews
                            (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                        },
                        { e ->
                            Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if (news_list.adapter == null) {
            news_list.adapter = NewsAdapter()
        }
    }
}