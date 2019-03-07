package com.workshop.aroundme.app.ui.categories

import com.workshop.aroundme.data.model.CategoryEntity

interface OnCategoryChildItemClickListener {
    fun OnCategoryChildClicked(categoryEntity: CategoryEntity)
}