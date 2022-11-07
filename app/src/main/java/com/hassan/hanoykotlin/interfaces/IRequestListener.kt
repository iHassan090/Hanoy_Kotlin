package com.hassan.hanoykotlin.interfaces

import org.json.JSONObject

interface IRequestListener {
    fun onResponse(response: JSONObject)

    fun onError(error: String)
}