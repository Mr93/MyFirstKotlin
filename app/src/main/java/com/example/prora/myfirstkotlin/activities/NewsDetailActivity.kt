package com.example.prora.myfirstkotlin.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.example.prora.myfirstkotlin.R
import kotlinx.android.synthetic.main.activity_news_detail.*

class NewsDetailActivity : AppCompatActivity() {

    companion object {
        private val INTENT_URL = "INTENT_URL"
        fun getMyIntent(context: Context, url: String): Intent {
            var intent = Intent(context, NewsDetailActivity::class.java)
            intent.putExtra(INTENT_URL, url)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)
//        news_web_view.settings.loadWithOverviewMode = true
//        news_web_view.settings.useWideViewPort = true
        news_web_view.webChromeClient = WebChromeClient()
        news_web_view.settings.javaScriptEnabled = true
        news_web_view.settings.userAgentString = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_3 like Mac OS X) AppleWebKit/600.14 (KHTML, like Gecko) Mobile/12F70"
        var intent = intent
        news_web_view.loadUrl(intent.getStringExtra(INTENT_URL))
    }
}
