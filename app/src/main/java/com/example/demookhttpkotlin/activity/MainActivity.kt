package com.example.demookhttpkotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.demookhttpkotlin.R
import com.example.demookhttpkotlin.`interface`.ApiCallListener
import com.example.demookhttpkotlin.databinding.ActivityMainBinding
import com.example.demookhttpkotlin.model.Post
import com.example.demookhttpkotlin.network.ApiManager

class MainActivity : AppCompatActivity() , ApiCallListener {
    private  var postList:List<Post>? =null
    private  var post:Post? =null
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        binding.post=Post(0,0,null,null)
        ApiManager.getInstance().readSinglePost(1,this)

    }



    override fun onCallBackSuccess(responseObject: Any, requestType: ApiManager.Companion.REQUEST_TYPE) {
        when(requestType){
            ApiManager.Companion.REQUEST_TYPE.REQUEST_READ_SINGLE_POST ->binding.post= responseObject as Post
            ApiManager.Companion.REQUEST_TYPE.REQUEST_CREATE_POST -> post= responseObject as Post
            ApiManager.Companion.REQUEST_TYPE.REQUEST_DELETE_POST -> post= responseObject as Post
            ApiManager.Companion.REQUEST_TYPE.REQUEST_READ_POST -> postList= responseObject as List<Post>
        }
        Log.d("response", postList.toString())
    }

    override fun onCallBackFailure(errorMessage: String) {
        Log.d("response", "onCallBackFailure: ")
    }


}