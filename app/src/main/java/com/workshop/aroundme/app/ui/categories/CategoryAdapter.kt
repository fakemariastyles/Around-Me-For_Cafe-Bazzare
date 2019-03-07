package com.workshop.aroundme.app.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.ParentCategoryEntity

class CategoryAdapter(private val items: List<ParentCategoryEntity>) : RecyclerView.Adapter<CategoryViewHolder>() {
    private val viewPool = RecyclerView.RecycledViewPool()
    var categoryView : View? = null
    var parentCategories = listOf<ParentCategoryEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_item , parent , false)
        categoryView = view
        return CategoryViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder : CategoryViewHolder, position: Int) {
        val parent =  items[position]
        (holder as CategoryViewHolder).bind(parent)
        val recyclerView = categoryView?.findViewById<RecyclerView>(R.id.nestedRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(categoryView?.context , LinearLayout.HORIZONTAL , false)
        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context , LinearLayout.HORIZONTAL , false)
        recyclerView?.adapter = CategoryChildAdapter(parent.children ?: listOf())
        recyclerView?.setRecycledViewPool(viewPool)
        }
}