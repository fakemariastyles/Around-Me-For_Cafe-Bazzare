package com.workshop.aroundme.app.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.data.model.ParentCategoryEntity

class CategoryFragment : Fragment() {
    private var adapter :CategoryAdapter? = null
    override fun onViewCreated(fragmentView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(fragmentView, savedInstanceState)
        val recyclerView =fragmentView.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(fragmentView.context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewFragment = inflater.inflate(R.layout.fragment_list, container, false)
        return viewFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val categoryRepository = Injector.provideCategoryRepository()
        categoryRepository.getCategories(::onCategoriesReady)
    }

    private fun onCategoriesReady(list: List<ParentCategoryEntity>?) {
        activity?.runOnUiThread {
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            println(list)
            adapter = CategoryAdapter(list ?: listOf())
            recyclerView?.adapter = adapter
            adapter?.parentCategories = list.orEmpty()
        }
    }
}