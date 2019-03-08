package com.workshop.aroundme.app.ui.categories.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.categories.OnCategoryChildItemClickListener
import com.workshop.aroundme.app.ui.categories.viewholders.CategoryViewHolder
import com.workshop.aroundme.data.model.ParentCategoryEntity

class CategoryAdapter(private val items: List<ParentCategoryEntity> ,
                      private val onCategoryChildItemClickListener: OnCategoryChildItemClickListener
)
    : RecyclerView.Adapter<CategoryViewHolder>() {
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
        recyclerView?.layoutManager = LinearLayoutManager(categoryView?.context)
        val childLayoutManager = LinearLayoutManager(holder.recyclerView.context)
        recyclerView?.adapter = CategoryChildAdapter(
            parent.children ?: listOf(),
            onCategoryChildItemClickListener
        )
        recyclerView?.setRecycledViewPool(viewPool)
        }
}