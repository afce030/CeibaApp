package com.ceiba.ceibaapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.ceiba.ceibaapp.R
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.view.fragments.PostsByUserFragmentDirections
import kotlinx.android.synthetic.main.post_list_item.view.*

class PostsAdapter(
    private val navController: NavController
): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    private val postsList = mutableListOf<PostsEntity>()

    fun addItems(posts: List<PostsEntity>, clean: Boolean = true) {

        if(clean){
            postsList.clear()
        }

        postsList.addAll(posts)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        return PostsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return postsList.size
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {

        val itemView = holder.itemView

        itemView.tv_postTitle.text = postsList[position].title
        itemView.tv_postText.text = postsList[position].body

        val action = PostsByUserFragmentDirections.actionPostsByUserFragmentToPostViewerFragment(
            postsList[position].postId?.toLong() ?: -1
        )

        itemView.setOnClickListener {
            navController.navigate(action)
        }

    }

    inner class PostsViewHolder(view: View): RecyclerView.ViewHolder(view)

}