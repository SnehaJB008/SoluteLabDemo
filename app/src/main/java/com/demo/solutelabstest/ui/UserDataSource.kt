package com.demo.solutelabstest.ui

import androidx.paging.PageKeyedDataSource
import com.demo.solutelabstest.modules.User
import com.demo.solutelabstest.network.RestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserDataSource(val client: RestClient?): PageKeyedDataSource<Int, User>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {
        client?.getUsers(1,10)?.enqueue(object : Callback<ArrayList<User>?> {
            override fun onResponse(
                call: Call<ArrayList<User>?>,
                response: Response<ArrayList<User>?>
            ) {
                if(response.code() == 200){
                    val userList: ArrayList<User>? = response.body()
                    callback.onResult(userList!!, null,2)
                }else{

                }
            }
            override fun onFailure(call: Call<ArrayList<User>?>, t: Throwable) {
            }
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        client?.getUsers(params.key,10)?.enqueue(object : Callback<ArrayList<User>?> {
            override fun onResponse(
                call: Call<ArrayList<User>?>,
                response: Response<ArrayList<User>?>
            ) {
                if(response.code() == 200){
                    val userList: ArrayList<User>? = response.body()
                    callback.onResult(userList!!, (params.key+ 1))
                }else{

                }
            }
            override fun onFailure(call: Call<ArrayList<User>?>, t: Throwable) {
            }
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {

    }
}