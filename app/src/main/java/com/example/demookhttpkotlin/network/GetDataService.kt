package com.example.demookhttpkotlin.network

import com.example.demookhttpkotlin.model.Post
import retrofit2.Call
import retrofit2.http.*


interface GetDataService {

    @GET("/posts")
    fun readAllPosts(): Call<List<Post>>

    @POST("/posts")
    fun createPost(@Body post: Post):Call<Post>

    @DELETE("/posts/{id}")
    fun deletePost(@Path("id") postId:String):Call<Post>
}