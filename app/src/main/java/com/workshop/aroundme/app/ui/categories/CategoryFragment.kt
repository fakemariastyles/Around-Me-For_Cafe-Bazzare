package com.workshop.aroundme.app.ui.categories

import android.app.AlertDialog
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.categories.adapters.CategoryAdapter
import com.workshop.aroundme.app.ui.categories.categoryChildDetail.CategoryChildDetailFragment
import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity
import kotlin.concurrent.thread

class CategoryFragment : Fragment() , OnCategoryChildItemClickListener{
    private var adapter :CategoryAdapter? = null
    override fun onViewCreated(fragmentView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(fragmentView, savedInstanceState)
        val recyclerView = fragmentView.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(fragmentView.context)
        view?.findViewById<View?>(R.id.searchButton)?.setOnClickListener() {
            if (fragmentView.findViewById<EditText>(R.id.searchEditText).text!!.isNotEmpty()) {
                val toBeSearched = fragmentView.findViewById<EditText>(R.id.searchEditText).text
                fragmentManager?.beginTransaction()
                    ?.replace(R.id.content_frame, SearchFragment.newInstance(toBeSearched.toString()))
                    ?.addToBackStack(null)
                    ?.commit()
            }else{
                AlertDialog.Builder(fragmentView.context)
                    .setTitle(getString(R.string.error))
                    .setMessage("Field Empty")
                    .setPositiveButton(getString(R.string.ok)) { dialogInterface: DialogInterface, i: Int ->
                        dialogInterface.dismiss()
                    }
                    .create()
                    .show()
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val viewFragment = inflater.inflate(R.layout.fragment_list, container, false)
        return viewFragment
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val categoryRepository = Injector.provideCategoryRepository(requireContext())
        categoryRepository.getCategories(::onCategoriesReady)
    }

    private fun onCategoriesReady(list: List<ParentCategoryEntity>?) {
        activity?.runOnUiThread {
            println(list)
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            adapter = CategoryAdapter(list ?: listOf(), this)
            recyclerView?.adapter = adapter
            adapter?.parentCategories = list.orEmpty()
            thread {
                Injector.provideCategoryRepository(requireContext()).saveCategories(list)
            }
        }
    }

    override fun OnCategoryChildClicked(categoryEntity: CategoryEntity) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame , CategoryChildDetailFragment.newInstance(categoryEntity.name))
            ?.addToBackStack(null)
            ?.commit()
    }
}