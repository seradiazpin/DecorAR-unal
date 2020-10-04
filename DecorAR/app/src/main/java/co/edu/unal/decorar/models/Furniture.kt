package co.edu.unal.decorar.models

data class Furniture (
    var nombre: String,
    var foto: String,
    var precio: String?,
    var descripcion: String?,
    var material: String?,
    var marca: String?,
    var modelo: Int?,
    var tiendas: List<String>?
){

}