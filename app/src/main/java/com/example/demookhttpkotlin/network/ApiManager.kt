package com.example.demookhttpkotlin.network

import android.util.Log
import com.example.demookhttpkotlin.`interface`.ApiCallListener
import com.example.demookhttpkotlin.model.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class ApiManager {
    private lateinit var clientInstance:ClientInstance
    private var retrofit: Retrofit

    constructor() {
        retrofit = ClientInstance.getInstance()
    }

    companion object{
        private  var instance=ApiManager()

        enum class REQUEST_TYPE {
            REQUEST_CREATE_POST,
            REQUEST_UPDATE_POST,
            REQUEST_READ_POST,
            REQUEST_DELETE_POST,
        }

        fun getInstance():ApiManager{
            return instance
        }
    }
    fun readPosts(apiCallListener: ApiCallListener){
        var service = retrofit.create(GetDataService::class.java)
        var call : Call<List<Post>> = service.readAllPosts()
        makeBackEndRequest(call,apiCallListener,REQUEST_TYPE.REQUEST_READ_POST)
    }
    fun createPost(post: Post,apiCallListener: ApiCallListener){
        var service = retrofit.create(GetDataService::class.java)
        var call : Call<Post> = service.createPost(post)
        makeBackEndRequest(call,apiCallListener,REQUEST_TYPE.REQUEST_CREATE_POST)
    }
    private fun <T> makeBackEndRequest(call:Call<T>,apiCallListener: ApiCallListener, requestType:REQUEST_TYPE){
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                Log.d("response", response.code().toString())
                if (response.code() == 201  || response.code() == 200 ) {
                    apiCallListener.onCallBackSuccess(response.body() as Any, requestType)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                apiCallListener.onCallBackFailure(t.message!!)
            }
        })
    }
}


