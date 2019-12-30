package com.ceiba.ceibaapp.model.persistence.entities.users

import androidx.room.*

@Entity(tableName = "users_table")
data class UsersEntity(

    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: Long? = null,

    @ColumnInfo(name = "website")
	val website: String? = null,

    @Embedded
	val addressEntity: AddressEntity? = null,

    @ColumnInfo(name = "phone")
	val phone: String? = null,

    @ColumnInfo(name = "name")
	val name: String? = null,

    @Embedded
	val company: CompanyEntity? = null,

    @ColumnInfo(name = "email")
	val email: String? = null,

    @ColumnInfo(name = "username")
	val username: String? = null

)