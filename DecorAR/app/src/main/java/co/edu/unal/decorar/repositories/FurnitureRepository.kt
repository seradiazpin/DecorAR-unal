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
                "Sofa Cama",
                "https://www.amoblando.co/uploads/amoblando/product_images/47/medium/sofa-cama-multifuncional-euro-con-brazos-microfibra_XO4qb.jpg",
                "$1.319.000",
                "Sofá cama con 5 posiciones, colchoneta gruesa adherida en su superficie y brazos removibles",
                "Color Gris, Tela y mamadera",
                "Amoblando",
                1,
                listOf<String>("https://www.amoblando.co/sofa-cama-multifuncional-euro-con-brazos-microfibra?gclid=CjwKCAjwiOv7BRBREiwAXHbv3JGvHRyi0uigmGJMoZU6ZU56m7ZZ03zeWU0V06kMVfkbBFwpd2AIthoCgIcQAvD_BwE#49","Tienda2"),
                Type.FURNITURE.ordinal
            )
        )
        dataSet.add(
            Furniture(
                2,
                "CLOSET TERA RTA",
                "https://www.amoblando.co/uploads/amoblando/product_images/367/full/closet-tera-rta_gAXhM.jpg",
                "$549.000",
                "Closet con tres entrepaños, doble zapatero y dos colgaderos para ropa",
                "Amoblando",
                "Ikea",
                2,
                listOf<String>("https://www.amoblando.co/closet-tera-rta#18?op=60521","Tienda2"),
                Type.FURNITURE.ordinal
            )
        )
        dataSet.add(
            Furniture(
                3,
                "TRAMONTINA",
                "https://falabella.scene7.com/is/image/FalabellaCO/3524096_1?wid=1500&hei=1500&qlt=70",
                "$328.900",
                "Banco Alto Naturale",
                "Madera",
                "Fallabela",
                3,
                listOf<String>("https://www.falabella.com.co/falabella-co/product/3524092/Banco-Alto-Naturale/3524096?ef_id=CjwKCAjwiOv7BRBREiwAXHbv3MThE5DcwNapOV1OorNwOF1PVA4C9RizK6VU4F0pmcVF2gma0Op8MxoCMQQQAvD_BwE:G:s&s_kwcid=AL!703!3!386927636577!!!u!949117518238!&kid=shopp991056338&gclid=CjwKCAjwiOv7BRBREiwAXHbv3MThE5DcwNapOV1OorNwOF1PVA4C9RizK6VU4F0pmcVF2gma0Op8MxoCMQQQAvD_BwE","Tienda2"),
                Type.FURNITURE.ordinal
            )
        )
        dataSet.add(
            Furniture(
                4,
                "Emilia",
                "https://www.alfa.com.co/wp-content/uploads/2020/06/225019839-1-2.jpg",
                "$36,480/Caja",
                "Cerámica en diferentes formatos ideal para interiores en pisos y paredes, exteriores en pisos, con diseño tipo piedra y tecnología RealHD.",
                "Madera",
                "Alfa",
                1,
                listOf<String>("https://www.alfa.com.co/producto/emilia/","Tienda2"),
                Type.FLOOR.ordinal

            )
        )
        dataSet.add(
            Furniture(
                5,
                "Piso Vigo",
                "https://www.alfa.com.co/wp-content/uploads/2020/06/225021569-1-1.jpg",
                "$37,875/Caja",
                "Cerámica ideal para pisos y paredes en ambientes interiores, con diseño tipo madera y tecnología RealHD.",
                "Madera",
                "Alfa",
                2,
                listOf<String>("https://www.alfa.com.co/producto/piso-vigo/","Tienda2"),
                Type.FLOOR.ordinal
            )
        )
    }
}