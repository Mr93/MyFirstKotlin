package com.example.prora.myfirstkotlin.commons

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

class InfinityScrollListener(val func: () -> Unit, val layoutManager: LinearLayoutManager) : RecyclerView
.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true
    private var visibleThreshold = 2
    private var firstVisibleItem = 0
    private var visiableItemCount = 0
    private var totalItemCount = 0


    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            visiableItemCount = recyclerView.childCount
            totalItemCount = layoutManager.itemCount
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
            Log.d("onScrolled", " $visiableItemCount $totalItemCount $firstVisibleItem $previousTotal")
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false
                    previousTotal = totalItemCount
                }
            }
            if (!loading && (totalItemCount - visiableItemCount) <= (firstVisibleItem + visibleThreshold)) {
                func()
                loading = true
            }
        }
    }

    fun resetPreviousTotal() {
        previousTotal = 0
    }
}