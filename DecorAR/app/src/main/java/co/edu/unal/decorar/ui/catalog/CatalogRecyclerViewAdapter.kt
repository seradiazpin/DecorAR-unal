package co.edu.unal.decorar.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import co.edu.unal.decorar.R
import co.edu.unal.decorar.models.Furniture
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_furniture_item.view.*

class CatalogRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var items: List<Furniture> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FurnitureViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_furniture_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FurnitureViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(furnitureList: List<Furniture> ){
        items = furnitureList
    }

    class FurnitureViewHolder constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView){
        val itemImage: ImageView = itemView.catalog_item_image
        val itemName: TextView = itemView.catalog_item_text

        fun bind(furniture: Furniture){
            itemName.text = furniture.nombre

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(furniture.foto)
                .into(itemImage)
        }
    }
}
