package com.workshop.aroundme.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.workshop.aroundme.local.dao.CategoryChildDao
import com.workshop.aroundme.local.dao.CategoryDao
import com.workshop.aroundme.local.dao.PlaceDao
import com.workshop.aroundme.local.model.LocalCategory
import com.workshop.aroundme.local.model.LocalParentCategory
import com.workshop.aroundme.local.model.LocalPlace

@Database(entities = [LocalPlace::class , LocalParentCategory::class , LocalCategory::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao():CategoryDao
    abstract fun categoryChildDao():CategoryChildDao
    abstract fun placeDao(): PlaceDao
}