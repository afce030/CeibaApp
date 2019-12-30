package com.ceiba.ceibaapp.model.persistence.methods

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceiba.ceibaapp.model.persistence.entities.users.UsersEntity

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(vararg users: UsersEntity)

    @Query("DELETE FROM users_table")
    suspend fun deleteUsers()

    @Query("SELECT * FROM users_table")
    fun getUsers(): LiveData<List<UsersEntity>>

    @Query("SELECT * FROM users_table WHERE username LIKE :pattern")
    fun getUsersbyPattern(pattern: String): LiveData<List<UsersEntity>>

}