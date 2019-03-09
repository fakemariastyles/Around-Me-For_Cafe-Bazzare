package com.workshop.aroundme.local.dao

import androidx.room.*
import com.workshop.aroundme.local.model.LocalCategory
import com.workshop.aroundme.local.model.LocalParentCategory

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category")
    fun listAll() : List<LocalParentCategory>?

    @Query("DELETE FROM category")
    fun clearAll()

    @Delete
    fun delete(vararg localParentCategory: LocalParentCategory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg localParentCategory : LocalParentCategory)

    @Update
    fun update(localParentCategory: LocalParentCategory)
}

@Dao
interface CategoryChildDao{
    @Query("SELECT * FROM childcategory WHERE parentId= :id")
    fun findChildernOfaParent (vararg id: Int?): List<LocalCategory>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(localCategory: LocalCategory)
}