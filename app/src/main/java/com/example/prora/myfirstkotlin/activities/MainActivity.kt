package com.example.prora.myfirstkotlin.activities

import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.prora.myfirstkotlin.R
import com.example.prora.myfirstkotlin.fragments.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            changeFragment(NewsFragment())
        }
    }

    private fun changeFragment(f: android.support.v4.app.Fragment, cleanStack: Boolean = false) {
        var fragmentTransaction = supportFragmentManager.beginTransaction()
        if (cleanStack) {
            clearBackStack()
        }
        fragmentTransaction.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit)
        fragmentTransaction.replace(R.id.activity_base_content, f)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    private fun clearBackStack() {
        var fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 0) {
            var first = fragmentManager.getBackStackEntryAt(0)
            fragmentManager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        if (fragmentManager.backStackEntryCount > 1) {
            fragmentManager.popBackStack()
        } else {
            finish()
        }
    }
}
