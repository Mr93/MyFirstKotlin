package com.example.prora.myfirstkotlin.commons

import android.support.v4.app.Fragment
import rx.subscriptions.CompositeSubscription

open class RxBaseFragment() : Fragment() {
    protected var subscriptions = CompositeSubscription()

    override fun onResume() {
        super.onResume()
        subscriptions = CompositeSubscription()
    }

    override fun onPause() {
        super.onPause()
        clearSubScription()
    }

    fun clearSubScription() {
        if (!subscriptions.isUnsubscribed) {
            subscriptions.unsubscribe()
        }
        subscriptions.clear()
    }

}