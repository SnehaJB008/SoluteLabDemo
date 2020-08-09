package com.demo.solutelabstest.ui

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.DataSource.Factory
import com.demo.solutelabstest.modules.User
import com.demo.solutelabstest.network.RestClient

class UserDataSourceFactory(private val client: RestClient?) : Factory<Int,User>() {
    var userDataSource: UserDataSource? = null
    var mutableLiveData: MutableLiveData<UserDataSource>? = null
    init {
        mutableLiveData = MutableLiveData()
    }
    override fun
            create(): DataSource<Int, User>? {
        userDataSource = UserDataSource(client)
        mutableLiveData?.postValue(userDataSource)
        return userDataSource
    }
}