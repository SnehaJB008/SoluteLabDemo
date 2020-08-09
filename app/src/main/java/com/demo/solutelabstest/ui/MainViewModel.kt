package com.demo.solutelabstest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.demo.solutelabstest.modules.User
import com.demo.solutelabstest.network.RestClient
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainViewModel(client: RestClient?): ViewModel(){
    var userDataSourceFactory: UserDataSourceFactory? = null
    var dataSourceMutableLiveData: MutableLiveData<UserDataSource>? = null
    private val bookMarkedUsers : MutableLiveData<ArrayList<User>> = MutableLiveData()
    var executor: Executor? = null

    private  var pagedListLiveData: LiveData<PagedList<User?>?>? = null

    init {
        userDataSourceFactory = UserDataSourceFactory(client)
        dataSourceMutableLiveData = userDataSourceFactory?.mutableLiveData
        bookMarkedUsers.value = ArrayList()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(10)
            .setPageSize(10)
            .setPrefetchDistance(4)
            .build()
        executor = Executors.newFixedThreadPool(5)
        pagedListLiveData =
            LivePagedListBuilder<Int, User>(userDataSourceFactory!!, config)
                .setFetchExecutor(executor!!)
                .build()
    }

    fun getPagedListLiveData(): LiveData<PagedList<User?>?>? {
        return pagedListLiveData
    }

    fun getBookMarkedUserList():LiveData<ArrayList<User>>{
        return  this.bookMarkedUsers
    }
    fun updateBookmarkUser(user:User){
        if(user.is_selected == true){
            bookMarkedUsers.value?.add(user)
            bookMarkedUsers.value = bookMarkedUsers.value
        }else{
            bookMarkedUsers.value?.remove(user)
            bookMarkedUsers.value = bookMarkedUsers.value
        }
    }


}