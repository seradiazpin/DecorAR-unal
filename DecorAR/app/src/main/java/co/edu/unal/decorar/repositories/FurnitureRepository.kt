package co.edu.unal.decorar.repositories

import androidx.lifecycle.MutableLiveData
import co.edu.unal.decorar.models.Furniture
import co.edu.unal.decorar.models.Type

object FurnitureRepository {
    private lateinit var dataSet : ArrayList<Furniture>

    fun getFurniture() : MutableLiveData<List<Furniture>>{
        setFurniture()
        val data : MutableLiveData<List<Furniture>> = MutableLiveData()
        data.value = dataSet
        return data
    }

    fun getSingleFurniture(id: Int) : Furniture{
        return dataSet[id]
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
                1,
                listOf<String>("https://www.ikea.com/","Tienda2"),
                Type.FURNITURE.ordinal
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
                2,
                listOf<String>("https://www.ikea.com/","Tienda2"),
                Type.FURNITURE.ordinal
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
                3,
                listOf<String>("https://www.ikea.com/","Tienda2"),
                Type.FURNITURE.ordinal
            )
        )
        dataSet.add(
            Furniture(
                4,
                "Piso Prueba 1",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                1,
                listOf<String>("https://www.ikea.com/","Tienda2"),
                Type.FLOOR.ordinal

            )
        )
        dataSet.add(
            Furniture(
                5,
                "Piso Prueba 2",
                "https://www.ikea.com/ca/en/images/products/stefan-chair-brown-black__0727320_PE735593_S5.jpg",
                "1500",
                "Un mueble",
                "Madera",
                "Ikea",
                2,
                listOf<String>("https://www.ikea.com/","Tienda2"),
                Type.FLOOR.ordinal
            )
        )
    }
}