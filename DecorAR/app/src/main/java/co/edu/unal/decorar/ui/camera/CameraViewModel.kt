package co.edu.unal.decorar.ui.camera

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import co.edu.unal.decorar.R
import co.edu.unal.decorar.models.Furniture

class CameraViewModel : ViewModel() {

    private val _elements = MutableLiveData<Map<Int, Int>>().apply {
        value = mapOf(1 to R.raw.model, 2 to R.raw.armoire, 3 to R.raw.chair)
    }
    private val _floors = MutableLiveData<Map<Int, Int>>().apply {
        value = mapOf( 1 to R.drawable.floortexture, 2 to R.drawable.floortexture2)
    }
    val elements: LiveData<Map<Int, Int>> = _elements
    val floors: LiveData<Map<Int, Int>> = _floors
}