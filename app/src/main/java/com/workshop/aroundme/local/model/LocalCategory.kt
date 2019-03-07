package com.workshop.aroundme.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class LocalCategory(
    @PrimaryKey val categoryId : Int?,
    val name : String,
    val imageIcon : String
)