package com.ceiba.ceibaapp.model.persistence.entities.users

import androidx.room.ColumnInfo

data class CompanyEntity(

	@ColumnInfo(name = "companyName")
	val companyName: String? = null,

	@ColumnInfo(name = "bs")
	val bs: String? = null,

	@ColumnInfo(name = "catchPhrase")
	val catchPhrase: String? = null

)