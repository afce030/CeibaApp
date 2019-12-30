package com.ceiba.ceibaapp.model.retrofit.webServices

import com.ceiba.ceibaapp.model.retrofit.dtos.users.UsersResponse
import retrofit2.Call
import retrofit2.http.GET

interface UsersWS {

    @GET("users/")
    fun getUsersFromAPI() : Call<List<UsersResponse>>

}