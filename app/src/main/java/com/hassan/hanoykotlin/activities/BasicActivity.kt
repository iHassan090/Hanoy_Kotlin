package com.hassan.hanoykotlin.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BasicActivity : AppCompatActivity() {

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        this.initValues()
        this.initValuesInViews()
        this.setOnViewClickListener()
    }

    open fun initValues() {

    }

    open fun initValuesInViews() {

    }

    open fun setOnViewClickListener() {

    }
}