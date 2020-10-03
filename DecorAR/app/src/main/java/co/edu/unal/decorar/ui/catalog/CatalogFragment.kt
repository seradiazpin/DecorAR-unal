package co.edu.unal.decorar.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import co.edu.unal.decorar.MainViewModel
import co.edu.unal.decorar.R
import co.edu.unal.decorar.ui.TopSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_catalog.*

class CatalogFragment : Fragment(){
    val args: CatalogFragmentArgs by navArgs()

    private lateinit var mainViewModel: MainViewModel
    private lateinit var catalogViewModel: CatalogViewModel

    private lateinit var catalogAdapter: CatalogRecyclerViewAdapter
    private val NUM_COLUMNS = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_catalog, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run{
            mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Throwable("invalid activity")
        mainViewModel.updateActionBarTitle(args.choosedType)
        initRecyclerView()
        catalogViewModel = ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        catalogViewModel.furniture.observe(viewLifecycleOwner){
            catalogAdapter.notifyDataSetChanged()
        }
        addDataSet()
    }

    private fun addDataSet(){
        val data = catalogViewModel.furniture.value!!
        catalogAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        catalog_recycler_view.layoutManager = StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL)
        val topSpacingItemDecoration = TopSpacingItemDecoration(30)
        catalog_recycler_view.addItemDecoration(topSpacingItemDecoration)
        catalogAdapter = CatalogRecyclerViewAdapter()
        catalog_recycler_view.adapter = catalogAdapter
    }
}