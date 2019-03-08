package com.workshop.aroundme.app.ui.categories.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.workshop.aroundme.R
import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import kotlinx.android.synthetic.main.item_category_item.view.*

class CategoryViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
    val recyclerView : RecyclerView = itemView.nestedRecyclerView
    val textView:TextView = itemView.parentCategoryName
    fun bind(parentCategoryEntity: ParentCategoryEntity){
        textView.text = parentCategoryEntity.name
    }
}