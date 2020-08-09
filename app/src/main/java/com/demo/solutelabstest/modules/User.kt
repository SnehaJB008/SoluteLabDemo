package com.demo.solutelabstest.modules

import androidx.recyclerview.widget.DiffUtil


data class User (
    var login: String? = null,
    val id :Int ? = null,
    val node_id: String? = null,
    val avatar_url: String? = null,
    val gravatar_id: String? = null,
    val url: String? = null,
    val html_url: String? = null,
    val followers_url: String? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val organizations_url: String? = null,
    val repos_url: String? = null,
    val events_url: String? = null,
    val received_events_url: String? = null,
    val type: String? = null,
    val site_admin :Boolean? = false,
    var is_selected : Boolean?= false
){
    companion object {
        val CALLBACK: DiffUtil.ItemCallback<User> = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id === newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return true
            }
        }
    }
}