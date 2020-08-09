package com.demo.solutelabstest.ui

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.solutelabstest.R
import com.demo.solutelabstest.modules.User
import kotlinx.android.synthetic.main.item_user.view.*

class BookMarkedUserListAdapter(val context: Context, var userList: ArrayList<User>,val listener : (User)->Unit):
    RecyclerView.Adapter<BookMarkedUserListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookMarkedUserListAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: BookMarkedUserListAdapter.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(position)
    }
    fun updateList(list: ArrayList<User>) {
        userList = list
        notifyDataSetChanged()
    }
    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(position: Int) {
            val user = userList.get(position)
            itemView.tvUserName.text = user.login
            itemView.checkbox.isChecked = user.is_selected?:false
            try {
                Glide.with(context as Activity)
                    .asBitmap()
                    .load(user.avatar_url)
                    .placeholder(R.drawable.ic_defaut_photo)
                    .into(itemView.ivUserProfilePic)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            itemView.setOnClickListener {
                user.is_selected = !(user.is_selected?:false)
                listener(user)
                notifyDataSetChanged()
            }
        }
    }
}