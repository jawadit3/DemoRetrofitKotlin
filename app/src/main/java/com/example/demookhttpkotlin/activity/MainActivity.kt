package com.example.demookhttpkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.demookhttpkotlin.R
import com.example.demookhttpkotlin.`interface`.ApiCallListener
import com.example.demookhttpkotlin.model.Post
import com.example.demookhttpkotlin.network.ApiManager

class MainActivity : AppCompatActivity() , ApiCallListener {
    private lateinit var postList:List<Post>
    private lateinit var post:Post
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiManager.getInstance().readPosts(this)
    }



    override fun onCallBackSuccess(responseObject: Any, requestType: ApiManager.Companion.REQUEST_TYPE) {
        when(requestType){
            ApiManager.Companion.REQUEST_TYPE.REQUEST_READ_POST -> postList= responseObject as List<Post>
            ApiManager.Companion.REQUEST_TYPE.REQUEST_CREATE_POST -> post= responseObject as Post
        }
        Log.d("response", postList.toString())
    }

    override fun onCallBackFailure(errorMessage: String) {
        Log.d("response", "onCallBackFailure: ")
    }


}