package com.ceiba.ceibaapp.model.retrofit.dtos.users

import com.google.gson.annotations.SerializedName

data class Geo(

	@field:SerializedName("lng")
	val lng: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null
)