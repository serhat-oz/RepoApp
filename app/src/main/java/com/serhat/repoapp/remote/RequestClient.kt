package com.serhat.repoapp.remote

import com.serhat.repoapp.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestClient {

    @GET("users/{user}/repos")
    fun getSpesificUsersRepo(@Path("user") value: String): Call<List<User>>

}