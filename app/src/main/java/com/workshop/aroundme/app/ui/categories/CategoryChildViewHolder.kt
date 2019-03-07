package com.workshop.aroundme.app.ui.categories

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity

class CategoryChildViewHolder (itemView : View) :
    RecyclerView.ViewHolder(itemView) {
    private val categoryTextView = itemView.findViewById<TextView>(R.id.childCategoryName)
    private val imageIcon = itemView.findViewById<ImageView>(R.id.iconImage)
    fun bind (categoryEntity: CategoryEntity , onCategoryChildItemClickListener: OnCategoryChildItemClickListener){
        categoryTextView.text = categoryEntity.name
        Picasso.get().load(categoryEntity.icon).into(imageIcon)
        categoryTextView.setOnClickListener{
            onCategoryChildItemClickListener.OnCategoryChildClicked(categoryEntity)
        }
    }
}