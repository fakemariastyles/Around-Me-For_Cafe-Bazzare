package com.workshop.aroundme.local.dao

import androidx.room.*
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.local.model.LocalCategory

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun listAll() : List<LocalCategory>

    @Query("DELETE FROM category")
    fun clearAll()

    @Delete
    fun delete(vararg localCategory: LocalCategory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg localCategory : LocalCategory)

    @Update
    fun update(localCategory: LocalCategory)
}