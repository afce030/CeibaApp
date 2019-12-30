package com.ceiba.ceibaapp.model.persistence.methods

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.relations.PostsAndUser

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(vararg posts: PostsEntity)

    @Query("DELETE FROM posts_table")
    suspend fun deletePosts()

    @Transaction
    @Query("SELECT * FROM users_table WHERE userId=:userId")
    fun getUserAndPosts(userId: Long): LiveData<List<PostsAndUser>>

    @Query("SELECT * FROM posts_table")
    fun getPosts(): LiveData<List<PostsEntity>>

    @Query("SELECT * FROM posts_table WHERE postId=:postId")
    fun getPostsById(postId: Long): LiveData<List<PostsEntity>>

}