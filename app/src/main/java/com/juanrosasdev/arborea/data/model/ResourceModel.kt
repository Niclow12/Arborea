package com.juanrosasdev.arborea.data.model

import com.google.gson.annotations.SerializedName

data class ResourceModel(
    @SerializedName("ID_Recursos") val idRecursos: Int,
    @SerializedName("ID_RecursoServidor") val idRecursoServidor: Int,
    @SerializedName("Email") val email: String,
    @SerializedName("Password") val password: String,
    @SerializedName("ID_Especie") val idEspecie: Int,
    @SerializedName("Ubicacion") val ubicacion: String,
    @SerializedName("AlturaUbicacion") val alturaUbicacion: String,
    @SerializedName("IdCategoria") val idCategoria: Int,
    @SerializedName("Imagen") val imagen: String,
    @SerializedName("FechaAlta") val fechaAlta: String,
    @SerializedName("Latitud") val latitud: String,
    @SerializedName("Longitud") val longitud: String,
    @SerializedName("ID_Calle") val idCalle: Int,
    @SerializedName("ID_EstadoRecurso") val idEstadoRecurso: Int,
    @SerializedName("UltimaActualizacion") val ultimaActualizacion: String

)
