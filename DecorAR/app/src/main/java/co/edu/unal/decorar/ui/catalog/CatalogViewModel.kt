package co.edu.unal.decorar.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.unal.decorar.models.Furniture
import co.edu.unal.decorar.repositories.FurnitureRepository

class CatalogViewModel : ViewModel() {
    private var _furniture = FurnitureRepository.getFurniture()

    val furniture: LiveData<List<Furniture>> = _furniture

}