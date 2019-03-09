package com.workshop.aroundme.data.repository

import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.local.datasource.CategoryLocalDataSource
import com.workshop.aroundme.remote.datasource.CategoryRemoteDataSource
import kotlin.concurrent.thread

class CategoryRepository(private val categoryRemoteDataSource: CategoryRemoteDataSource , private val categoryLocalDataSource: CategoryLocalDataSource) {

    fun getCategories(success: (List<ParentCategoryEntity>?) -> Unit) {
        thread {
            val categories = categoryRemoteDataSource.getCategories()
            success(categories)
        }
    }

    fun saveCategories(list: List<ParentCategoryEntity>?){
        thread {
            categoryLocalDataSource.saveCategories(list)
        }
    }
    fun getCategoriesFromDataBaseBySearch(s : String?,success: (List<ParentCategoryEntity>?) -> Unit){
        thread {
            var list = categoryLocalDataSource.searchCategories(s)
            success(list)
        }
    }
}