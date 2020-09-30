package co.edu.unal.decorar.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.edu.unal.decorar.R

class CatalogFragment : Fragment() {

    private lateinit var catalogViewModel: CatalogViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        catalogViewModel =
                ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_catalog, container, false)
        val textView: TextView = root.findViewById(R.id.text_catalog)
        catalogViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}