package com.demo.solutelabstest.network

import com.demo.solutelabstest.modules.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestClient {
    @GET("users")
    fun getUsers(@Query("page") pageNo:Int,@Query("per_page") perPage:Int): Call<ArrayList<User>?>?
}