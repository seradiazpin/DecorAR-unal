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
                1,
                "Mueble Prueba1",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                125,
                listOf<String>("Tienda1","Tienda2")
            )
        )
        dataSet.add(
            Furniture(
                2,
                "Mueble Prueba2",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                125,
                listOf<String>("Tienda1","Tienda2")
            )
        )
        dataSet.add(
            Furniture(
                3,
                "Mueble Prueba3",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                125,
                listOf<String>("Tienda1","Tienda2")
            )
        )
        dataSet.add(
            Furniture(
                4,
                "Mueble Prueba4",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                125,
                listOf<String>("Tienda1","Tienda2")
            )
        )
        dataSet.add(
            Furniture(
                5,
                "Mueble Prueba1",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                125,
                listOf<String>("Tienda1","Tienda2")
            )
        )
        dataSet.add(
            Furniture(
                6,
                "Mueble Prueba1",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                125,
                listOf<String>("Tienda1","Tienda2")
            )
        )
        dataSet.add(
            Furniture(
                7,
                "Mueble Prueba1",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                125,
                listOf<String>("Tienda1","Tienda2")
            )
        )
        dataSet.add(
            Furniture(
                8,
                "Mueble Prueba1",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                125,
                listOf<String>("Tienda1","Tienda2")
            )
        )
    }
}