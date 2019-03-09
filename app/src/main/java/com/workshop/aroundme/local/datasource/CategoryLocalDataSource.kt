package com.workshop.aroundme.local.datasource

import com.google.gson.Gson
import com.workshop.aroundme.data.mapper.*
import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import com.workshop.aroundme.local.dao.CategoryChildDao
import com.workshop.aroundme.local.dao.CategoryDao

class CategoryLocalDataSource(private val categoryDao: CategoryDao , private val categoryChildDao: CategoryChildDao) {
    fun getCategories() : List<ParentCategoryEntity>?{
        return categoryDao.listAll()
            ?.map {
                var childern = categoryChildDao.findChildernOfaParent(it.id)
                    ?.map {
                        it.toCategoryEntity()
                    }
                it.toParentCategoryEntity(children = childern)
            }
    }
    fun saveCategories(list : List<ParentCategoryEntity>?){
        list.let {
            for (x in list!!) {
                categoryDao.insert(x.toLocalParentCategory())
                val childern = x.children
                childern.let {
                    for( y in childern!! ){
                        categoryChildDao.insert(y.toLocalCategory(x.Id))
                    }
                }
            }
        }
    }
    fun searchCategories(s : String?) : List<ParentCategoryEntity>?{
        var list = getCategories()
        var found = mutableListOf<ParentCategoryEntity>()
        list.let {
            for (x in list!!){
                var childernFound = mutableListOf<CategoryEntity>()
                x.children.let {
                    for (y in x.children!!){
                        y.name.let {
                            print(y.name)
                            if(y.name!!.contains(s ?: "")){
                                childernFound.add(y)
                            }
                        }
                    }
                    }
                if (childernFound.isNotEmpty()) {
                    found.add(ParentCategoryEntity(name = x.name , Id = x.Id , children = childernFound))
                }
            }
            }
        println(found)
        return found
        }
    }

