package com.ceiba.ceibaapp.model.persistence.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity

data class PostsAndUser(

    @Embedded
    val usersEntity: UsersEntity,

    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val postsEntities: List<PostsEntity>

)