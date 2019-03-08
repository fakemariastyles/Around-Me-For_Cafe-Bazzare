package com.workshop.aroundme.local.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.workshop.aroundme.data.model.CategoryEntity

@Entity(tableName = "category")
data class LocalParentCategory (
    @PrimaryKey (autoGenerate = true) val id: Int ,
    val name:String?)

@Entity(tableName = "childcategory")
data class LocalCategory(
    val icon: String?,
    @PrimaryKey val id: Int?,
    val ParentId : Int?,
    val name: String?
)