package com.ceiba.ceibaapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import com.ceiba.ceibaapp.view.fragments.UserListFragmentDirections
import kotlinx.android.synthetic.main.user_list_item.view.*

class UsersAdapter(
    private val navController: NavController
): RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var usersList = mutableListOf<UsersEntity>()

    fun addItems(users: List<UsersEntity>, clean: Boolean = true) {

        if (clean){
            usersList.clear()
        }

        usersList.addAll(users)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {

        val itemView = holder.itemView

        itemView.tv_username.text = usersList[position].username
        itemView.tv_email.text = usersList[position].email
        itemView.tv_phone.text = usersList[position].phone

        val action = UserListFragmentDirections.actionUserListFragmentToPostsByUserFragment(
            usersList[position].userId ?: -1
        )

        itemView.tv_watchPosts.setOnClickListener {
            navController.navigate(action)
        }

    }

    inner class UsersViewHolder(view: View): RecyclerView.ViewHolder(view)

}