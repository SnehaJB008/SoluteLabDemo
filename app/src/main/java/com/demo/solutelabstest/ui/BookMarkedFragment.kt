package com.demo.solutelabstest.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.solutelabstest.R
import com.demo.solutelabstest.modules.User
import com.demo.solutelabstest.utils.Utils
import kotlinx.android.synthetic.main.fragment_book_marked.*
import org.koin.android.ext.android.get


class BookMarkedFragment : Fragment() {
    var mViewModel: MainViewModel? = null
    val utils = Utils()
    var bookMarkedUserListAdapter :BookMarkedUserListAdapter ?= null
    var userList : ArrayList<User> ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_book_marked, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = get()
        if(utils.checkInternetConnection(activity!!)){
            getAllUsers()
        }else{
            utils.showToast(activity,getString(R.string.connection_err))
        }
        init()
    }

    private fun init() {
        search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }
            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {

            }
            override fun afterTextChanged(s: Editable) {
                filter(s)
            }
        })
    }
    fun filter(text: CharSequence) {
        val temp: ArrayList<User> = ArrayList()
        for (d in userList?: ArrayList()) {
            if (d.login?.contains(text,true) == true) {
                temp.add(d)
            }
        }
        if(temp.size > 0){
            tvNoDataFound.visibility = View.GONE
        }else{
            tvNoDataFound.visibility = View.VISIBLE
        }
        bookMarkedUserListAdapter?.updateList(temp)
    }
    private fun getAllUsers() {
        mViewModel?.getBookMarkedUserList()?.observe(this, Observer { response ->
            userList = response as ArrayList<User>
            if(userList!!.size > 0 ){
                tvNoDataFound.visibility = View.GONE
                setData(userList!!)
            }else{
                tvNoDataFound.visibility = View.VISIBLE
            }
        })
    }
    private fun setData(userList: ArrayList<User>) {
        rvBookMarkedUsers.layoutManager = LinearLayoutManager(activity)
        bookMarkedUserListAdapter =  BookMarkedUserListAdapter(activity as Context,userList){
            mViewModel?.updateBookmarkUser(it)
        }
        rvBookMarkedUsers.adapter = bookMarkedUserListAdapter
    }
}