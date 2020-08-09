package com.demo.solutelabstest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.solutelabstest.R
import com.demo.solutelabstest.modules.User
import kotlinx.android.synthetic.main.item_user.view.*


class UserAdapter(val listener : (User)->Unit) :
    PagedListAdapter<User, UserAdapter.ViewHolder?>(User.CALLBACK) {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        i: Int
    ): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val view: View = layoutInflater.inflate(R.layout.item_user, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        i: Int
    ) {
        (holder as ViewHolder).bind(i)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            val user = getItem(position)
            itemView.tvUserName.text = user?.login
            itemView.checkbox.isChecked = user?.is_selected?:false

            try {
                Glide.with(itemView.context)
                    .asBitmap()
                    .load(user?.avatar_url)
                    .placeholder(R.drawable.ic_defaut_photo)
                    .into(itemView.ivUserProfilePic)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            itemView.setOnClickListener {
                user?.is_selected = !(user?.is_selected?:false)
                listener(user!!)
                notifyItemChanged(position)
            }
        }
    }
}