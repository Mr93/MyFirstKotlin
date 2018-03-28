package com.example.prora.myfirstkotlin.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.prora.myfirstkotlin.R
import com.example.prora.myfirstkotlin.adapaters.NewsAdapter
import com.example.prora.myfirstkotlin.commons.inflate
import kotlinx.android.synthetic.main.news_fragment.*


class NewsFragment : Fragment() {

    val TAG = "NewsFragment"

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.news_fragment)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        news_list.setHasFixedSize(true)
        news_list.layoutManager = LinearLayoutManager(context)
        initAdapter()
    }

    private fun initAdapter() {
        if (news_list.adapter == null) {
            Log.d(TAG, "aaaaaa")
            news_list.adapter = NewsAdapter()
        }
    }
}