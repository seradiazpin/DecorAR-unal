package co.edu.unal.decorar.repositories

import androidx.lifecycle.MutableLiveData
import co.edu.unal.decorar.models.Furniture

object FurnitureRepository {
    private lateinit var dataSet : ArrayList<Furniture>

    fun getFurniture() : MutableLiveData<List<Furniture>>{
        setFurniture()
        val data : MutableLiveData<List<Furniture>> = MutableLiveData()
        data.value = dataSet
        return data
    }

    private fun setFurniture(){
        dataSet = ArrayList()
        dataSet.add(
            Furniture(
                "Mueble Prueba1",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg"
            )
        )
        dataSet.add(
            Furniture(
                "Mueble Prueba2",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg"
            )
        )
        dataSet.add(
            Furniture(
                "Mueble Prueba3",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg"
            )
        )
        dataSet.add(
            Furniture(
                "Mueble Prueba4",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg"
            )
        )
        dataSet.add(
            Furniture(
                "Mueble Prueba",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg"
            )
        )
        dataSet.add(
            Furniture(
                "Mueble Prueba",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg"
            )
        )
        dataSet.add(
            Furniture(
                "Mueble Prueba",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg"
            )
        )
        dataSet.add(
            Furniture(
                "Mueble Prueba",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg"
            )
        )
    }
}