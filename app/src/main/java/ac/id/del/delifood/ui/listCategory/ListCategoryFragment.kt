package ac.id.del.delifood.ui.listCategory

import ac.id.del.delifood.databinding.FragmentListCategoryBinding
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager

class ListCategoryFragment
    : Fragment(), ListCategoryRecyclerViewClickListener {

    private var recipeMainCategoryList: ArrayList<String> = arrayListOf()
    private lateinit var originMainCategory: String

    private var _binding: FragmentListCategoryBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCategoryBinding.inflate(inflater, container, false)

        recipeMainCategoryList = arguments?.getStringArrayList("title_recipe")!!
        originMainCategory = arguments?.getString("origin")!!

        Log.d("ListCategory", recipeMainCategoryList[1])
        Log.d("ListCategory", originMainCategory)

        binding.titleListCategory.text = originMainCategory

        val listCategoryAdapter = ListCategoryAdapter(recipeMainCategoryList)
        listCategoryAdapter.listener = this

        val listCategoryView = binding.listCategory
        listCategoryView.apply {
            this.adapter = listCategoryAdapter
            this.layoutManager =  LinearLayoutManager(context)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(view: View, recipeMainCategory: String) {
//        MainRecipe()
//        view.findNavController().navigate(R.id.action_ListCategoryFragment_to_MainRecipeFragment)
        Log.d("ListCategoryFragment","Success")
    }
}