package com.workshop.aroundme.app.ui.categories.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.ui.categories.OnCategoryChildItemClickListener
import com.workshop.aroundme.app.ui.categories.viewholders.CategoryChildViewHolder
import com.workshop.aroundme.data.model.CategoryEntity

class CategoryChildAdapter (private val children : List <CategoryEntity> ,
                            private val onCategoryChildItemClickListener: OnCategoryChildItemClickListener
):
    RecyclerView.Adapter<CategoryChildViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_child_item , parent, false)
        return CategoryChildViewHolder(view)
    }

    override fun getItemCount(): Int = children.size

    override fun onBindViewHolder(holder: CategoryChildViewHolder, position: Int) {
        val child = children[position]
        holder.bind(child , onCategoryChildItemClickListener)
    }
    
}
