package com.demo.solutelabstest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.solutelabstest.R
import com.demo.solutelabstest.modules.User
import com.demo.solutelabstest.utils.Utils
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.progress.*
import org.koin.android.ext.android.get

class HomeFragment : Fragment() {
    var mViewModel: MainViewModel? = null
    val utils = Utils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = get()
        if(utils.checkInternetConnection(activity!!)){
            getAllUsers()
        }else{
            utils.showToast(activity,getString(R.string.connection_err))
        }
    }

    private fun getAllUsers() {
        toggleProgressbar(1)
        mViewModel?.getPagedListLiveData()?.observe(this, Observer { response ->
            val userList = response as PagedList<User>
            setData(userList)
            toggleProgressbar(0)
        })
    }

    private fun setData(userList: PagedList<User>) {
        rvUserList.layoutManager = LinearLayoutManager(activity)
        val userAdapter = UserAdapter(){
            mViewModel?.updateBookmarkUser(it)
        }
        userAdapter.submitList(userList)
        rvUserList.adapter = userAdapter
    }

    fun toggleProgressbar(visibility: Int) {
        try {
            if (visibility == 1) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}