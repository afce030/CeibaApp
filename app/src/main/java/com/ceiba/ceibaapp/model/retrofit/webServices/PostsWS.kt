package com.ceiba.ceibaapp.model.retrofit.webServices

import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import retrofit2.Call
import retrofit2.http.GET

interface PostsWS {

    @GET("posts/")
    fun getPostsFromAPI() : Call<List<PostsEntity>>

}