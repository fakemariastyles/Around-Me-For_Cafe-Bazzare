package com.workshop.aroundme.local.datasource

import com.google.gson.Gson
import com.workshop.aroundme.data.mapper.toCategoryEntity
import com.workshop.aroundme.data.mapper.toLocalCategory
import com.workshop.aroundme.data.mapper.toLocalParentCategory
import com.workshop.aroundme.data.mapper.toParentCategoryEntity
import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.local.dao.CategoryChildDao
import com.workshop.aroundme.local.dao.CategoryDao

class CategoryLocalDataSource(private val categoryDao: CategoryDao , private val categoryChildDao: CategoryChildDao) {
    fun getCategories() : List<ParentCategoryEntity>?{

        var list = categoryDao.listAll()
        var l = mutableListOf<ParentCategoryEntity>()
        var c = mutableListOf<CategoryEntity>()
        for ( x in list){
            var children = categoryChildDao.findChildernOfaParent(x.id)
            children.let {
                for (y in children!!){
                    c.add(y.toCategoryEntity())
                }
                l.add(x.toParentCategoryEntity(c))
            }
        }
        return l
    }
    fun saveCategories(list : List<ParentCategoryEntity>?){
        list.let {
            for(x in list!!) {
                val xLocalParentCategory = x.toLocalParentCategory()
//                println(xLocalParentCategory)
                categoryDao.insert(xLocalParentCategory)
//                println(categoryDao.listAll())

                x.children.let {
                    for (y in x.children!!){
                        categoryChildDao.insert(y.toLocalCategory(xLocalParentCategory.id))
                }}
            }
        }
    }
}