package com.example.demookhttpkotlin.`interface`

import com.example.demookhttpkotlin.network.ApiManager

interface ApiCallListener {
    fun onCallBackSuccess(responseObject: Any , requestType: ApiManager.Companion.REQUEST_TYPE)
    fun onCallBackFailure(errorMessage:String)
}