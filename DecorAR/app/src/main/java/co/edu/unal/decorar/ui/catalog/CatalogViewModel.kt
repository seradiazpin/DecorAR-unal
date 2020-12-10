package co.edu.unal.decorar.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.unal.decorar.models.Furniture
import co.edu.unal.decorar.repositories.FurnitureRepository

class CatalogViewModel() : ViewModel() {
    private lateinit var mFurnitures: MutableLiveData<List<Furniture>>
    val furnitures: LiveData<List<Furniture>>
        get() = mFurnitures

    fun init(type: Int){
        mFurnitures = FurnitureRepository.getFurniture(type)
    }
    fun search(s: String): MutableList<Furniture>{
        val furnituresFiltered = mutableListOf<Furniture>()
        for( f in mFurnitures.value!!){
            if(f.nombre.toLowerCase().contains(s.toLowerCase())){
                furnituresFiltered.add(f)
            }
        }
        return furnituresFiltered
    }
}