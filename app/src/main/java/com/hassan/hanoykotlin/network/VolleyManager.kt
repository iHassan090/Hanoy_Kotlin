package com.hassan.hanoykotlin.network

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.hassan.hanoykotlin.interfaces.IRequestListener

class VolleyManager private constructor(context: Context) {

    companion object {
        private var instance: VolleyManager? = null
        fun getInstance(context: Context): VolleyManager {
            if (instance == null)
                instance = VolleyManager(context)
            return instance!!
        }
    }

    private var mRequestQueue: RequestQueue? = null

    init {
        this.mRequestQueue = Volley.newRequestQueue(context)
    }

    fun sendApiCall(url: String, mListener: IRequestListener) {
        println("Request URL: $url")
        val jsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                println("Request Response: $response")
                mListener.onResponse(response)
            }) { error ->
            println("Request Error: " + error.message)
            mListener.onError(error.message!!)
        }
        mRequestQueue!!.add(jsonRequest)
    }
}