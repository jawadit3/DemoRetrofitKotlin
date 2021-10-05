package com.example.demookhttpkotlin.network

import com.example.demookhttpkotlin.model.Post
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface GetDataService {

    @GET("/posts")
    fun readAllPosts(): Call<List<Post>>

    @POST("/posts")
    fun createPost(@Body post: Post):Call<Post>
}