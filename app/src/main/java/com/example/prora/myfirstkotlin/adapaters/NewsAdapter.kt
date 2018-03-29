package com.example.prora.myfirstkotlin.adapaters

import android.support.v4.util.SparseArrayCompat
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.prora.myfirstkotlin.commons.RedditNewsItem

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>

    private val delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()

    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstant.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstant.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstant.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType).onCreateViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position)).onBindViewHolder(holder, items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return items.get(position).getViewType()
    }

    fun addNews(news: List<RedditNewsItem>) {
        items.addAll(0, news)
        notifyItemRangeInserted(0, news.size)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPostion())
        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<RedditNewsItem> {
        return items.filter { it.getViewType() == AdapterConstant.NEWS }
                .map { it as RedditNewsItem }
    }

    private fun getLastPostion() = if (items.lastIndex == -1) 0 else items.lastIndex
}