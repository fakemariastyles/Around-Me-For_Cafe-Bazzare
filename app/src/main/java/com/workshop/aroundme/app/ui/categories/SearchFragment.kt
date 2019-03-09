package com.workshop.aroundme.app.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.workshop.aroundme.R
import com.workshop.aroundme.app.Injector
import com.workshop.aroundme.app.ui.categories.adapters.CategoryAdapter
import com.workshop.aroundme.app.ui.categories.categoryChildDetail.CategoryChildDetailFragment
import com.workshop.aroundme.data.model.CategoryEntity
import com.workshop.aroundme.data.model.ParentCategoryEntity

class SearchFragment : Fragment() , OnCategoryChildItemClickListener {
    var toBeSearched : String? = null
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        toBeSearched = arguments?.getString(KEY_TO_BE_SEARCHED)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val categoryRepository = Injector.provideCategoryRepository(requireContext())
        categoryRepository.getCategoriesFromDataBaseBySearch(toBeSearched , ::OnCategoriesReadyDataBase)
        }


    private fun OnCategoriesReadyDataBase(list: List<ParentCategoryEntity>?){
        activity?.runOnUiThread{
            println(list)
            val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
            adapter = CategoryAdapter(list ?: listOf(), this)
            recyclerView?.adapter = adapter
            adapter?.parentCategories = list.orEmpty()
        }
    }
    override fun OnCategoryChildClicked(categoryEntity: CategoryEntity) {
        fragmentManager?.beginTransaction()
            ?.replace(R.id.content_frame , CategoryChildDetailFragment.newInstance(categoryEntity.name))
            ?.addToBackStack(null)
            ?.commit()
    }
companion object {
    fun newInstance(s: String?): SearchFragment {
        return SearchFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_TO_BE_SEARCHED, s)
            }
        }
    }
    const val KEY_TO_BE_SEARCHED = "toBeSearched"
}
}