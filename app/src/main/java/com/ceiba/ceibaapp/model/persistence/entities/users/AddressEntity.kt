package com.ceiba.ceibaapp.model.persistence.entities.users

import androidx.room.ColumnInfo

data class AddressEntity(

	@ColumnInfo(name = "zipcode")
	val zipcode: String? = null,

	@ColumnInfo(name = "lng")
	val lng: String? = null,

	@ColumnInfo(name = "lat")
	val lat: String? = null,

	@ColumnInfo(name = "suite")
	val suite: String? = null,

	@ColumnInfo(name = "city")
	val city: String? = null,

	@ColumnInfo(name = "street")
	val street: String? = null
)