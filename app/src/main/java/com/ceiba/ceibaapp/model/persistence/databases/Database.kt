package com.ceiba.ceibaapp.model.persistence.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceiba.ceibaapp.model.persistence.entities.posts.PostsEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.AddressEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.CompanyEntity
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity
import com.ceiba.ceibaapp.model.persistence.methods.PostsDao
import com.ceiba.ceibaapp.model.persistence.methods.UsersDao

@Database(entities = [
    PostsEntity::class,
    UsersEntity::class
], version = 1, exportSchema = false)
abstract class Database: RoomDatabase() {

    abstract fun postsDao() : PostsDao
    abstract fun usersDao() : UsersDao

}