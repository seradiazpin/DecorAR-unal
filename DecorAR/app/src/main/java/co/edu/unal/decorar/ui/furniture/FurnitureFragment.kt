package co.edu.unal.decorar.ui.furniture

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import co.edu.unal.decorar.R


class FurnitureFragment :  Fragment(){
    private lateinit var FurnitureViewModel: FurnitureViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FurnitureViewModel =
            ViewModelProviders.of(this).get(FurnitureViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_furniture, container, false)
        val nameView: TextView = root.findViewById(R.id.furniture_name)
        val imageView: ImageView = root.findViewById(R.id.furniture_image)
        val priceView: TextView = root.findViewById(R.id.furniture_price)
        val descriptionView: TextView = root.findViewById(R.id.furniture_description)
        val materialView: TextView = root.findViewById(R.id.furniture_material)
        val brandView: TextView = root.findViewById(R.id.furniture_brand)
        FurnitureViewModel.text.observe(viewLifecycleOwner, Observer {
            nameView.text = it
        })
        return root
    }
}