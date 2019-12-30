package com.ceiba.ceibaapp.model.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import com.ceiba.ceibaapp.model.persistence.relations.PostsAndUser

interface MainRepository {

    //Retrofit
    fun requestUsersInfo()
    fun requestPostsById(userId: Long)

    //Room insert
    fun insertUsersRoom(users: List<UsersEntity>)
    fun insertPostsRoom(posts: List<PostsEntity>)
    //Room delete
    fun deleteAllUsers()
    fun deleteAllPosts()

    //Room get info
    fun getAllUsersFromRoom(): LiveData<List<UsersEntity>>
    fun getAllUsersbyPatternFromRoom(pattern: String): LiveData<List<UsersEntity>>
    fun getPostsByUser(userId: Long): LiveData<List<PostsAndUser>>
    fun getPostsById(postId: Long): LiveData<List<PostsEntity>>


}