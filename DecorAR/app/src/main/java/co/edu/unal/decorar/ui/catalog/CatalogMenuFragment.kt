package co.edu.unal.decorar.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import co.edu.unal.decorar.R

class CatalogMenuFragment : Fragment() {

    private lateinit var catalogViewModel: CatalogViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        catalogViewModel =
                ViewModelProviders.of(this).get(CatalogViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_catalog_menu, container, false)

        val buttonFurniture: Button = root.findViewById(R.id.furniture_catalog)
        buttonFurniture.setOnClickListener{
            it.findNavController().navigate(R.id.navigation_catalog)
        }
        val buttonWall: Button = root.findViewById(R.id.wall_catalog)
        buttonWall.setOnClickListener{
            it.findNavController().navigate(R.id.navigation_catalog)
        }
        val buttonFloor: Button = root.findViewById(R.id.floor_catalog)
        buttonFloor.setOnClickListener{
            it.findNavController().navigate(R.id.navigation_catalog)
        }
        return root
    }



}