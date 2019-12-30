package com.ceiba.ceibaapp.model.retrofit.dtos.users

import com.ceiba.ceibaapp.model.persistence.entities.users.AddressEntity
import com.google.gson.annotations.SerializedName

data class UsersResponse(

    @field:SerializedName("website")
	val website: String? = null,

    @field:SerializedName("address")
	val address: AddressEntity? = null,

    @field:SerializedName("phone")
	val phone: String? = null,

    @field:SerializedName("name")
	val name: String? = null,

    @field:SerializedName("company")
	val company: Company? = null,

    @field:SerializedName("id")
	val id: Int? = null,

    @field:SerializedName("email")
	val email: String? = null,

    @field:SerializedName("username")
	val username: String? = null
)