package com.workshop.aroundme.app.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.CategoryEntity
import kotlinx.android.synthetic.main.item_category_child_item.view.*

class CategoryChildAdapter (private val children : List <CategoryEntity>) : RecyclerView.Adapter<CategoryChildViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category_child_item , parent, false)
        return CategoryChildViewHolder(view)
    }

    override fun getItemCount(): Int = children.size

    override fun onBindViewHolder(holder: CategoryChildViewHolder, position: Int) {
        val child = children[position]
        holder.bind(child)
    }

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val textView : TextView = itemView.childCategoryName
        val imageView: ImageView = itemView.iconImage

    }
}
