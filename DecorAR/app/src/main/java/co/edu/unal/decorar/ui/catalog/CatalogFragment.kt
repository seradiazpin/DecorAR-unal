package co.edu.unal.decorar.ui.catalog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import co.edu.unal.decorar.MainViewModel
import co.edu.unal.decorar.R

class CatalogFragment : Fragment(){
    private lateinit var mainViewModel: MainViewModel
    val args: CatalogFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args: CatalogFragmentArgs by navArgs()
        val root = inflater.inflate(R.layout.fragment_catalog, container, false)
        val textView: TextView = root.findViewById(R.id.testCatalog)
        textView.text = args.choosedType
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.run{
            mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Throwable("invalid activity")
        mainViewModel.updateActionBarTitle(args.choosedType)
    }
}