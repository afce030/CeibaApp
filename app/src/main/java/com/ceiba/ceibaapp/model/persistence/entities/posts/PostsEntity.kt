package com.ceiba.ceibaapp.model.persistence.entities.posts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_table")
data class PostsEntity(

	@PrimaryKey(autoGenerate = false)
	@ColumnInfo(name = "postId")
	val postId: Int? = null,

	@ColumnInfo(name = "userId")//Relation variable
	val userId: Int? = null,

	@ColumnInfo(name = "title")
	val title: String? = null,

	@ColumnInfo(name = "body")
	val body: String? = null

)