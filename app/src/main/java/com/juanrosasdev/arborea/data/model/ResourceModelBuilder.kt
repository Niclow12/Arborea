package com.juanrosasdev.arborea.data.model

class ResourceModelBuilder {
    private var idRecursos: Int = 0
    private var idRecursoServidor: Int = 0
    private var email: String = ""
    private var password: String = ""
    private var idEspecie: Int = 0
    private var ubicacion: String = ""
    private var alturaUbicacion: String = ""
    private var idCategoria: Int = 0
    private var imagen: String = ""
    private var fechaAlta: String = ""
    private var latitud: String = ""
    private var longitud: String = ""
    private var idCalle: Int = 0
    private var idEstadoRecurso: Int = 0
    private var ultimaActualizacion: String = ""

    fun idRecursos(idRecursos: Int) = apply { this.idRecursos = idRecursos }
    fun idRecursoServidor(idRecursoServidor: Int) =
        apply { this.idRecursoServidor = idRecursoServidor }

    fun email(email: String) = apply { this.email = email }
    fun password(password: String) = apply { this.password = password }
    fun idEspecie(idEspecie: Int) = apply { this.idEspecie = idEspecie }
    fun ubicacion(ubicacion: String) = apply { this.ubicacion = ubicacion }
    fun alturaUbicacion(alturaUbicacion: String) = apply { this.alturaUbicacion = alturaUbicacion }
    fun idCategoria(idCategoria: Int) = apply { this.idCategoria = idCategoria }
    fun imagen(imagen: String) = apply { this.imagen = imagen }
    fun fechaAlta(fechaAlta: String) = apply { this.fechaAlta = fechaAlta }
    fun latitud(latitud: String) = apply { this.latitud = latitud }
    fun longitud(longitud: String) = apply { this.longitud = longitud }
    fun idCalle(idCalle: Int) = apply { this.idCalle = idCalle }
    fun idEstadoRecurso(idEstadoRecurso: Int) = apply { this.idEstadoRecurso = idEstadoRecurso }
    fun ultimaActualizacion(ultimaActualizacion: String) =
        apply { this.ultimaActualizacion = ultimaActualizacion }

    fun build() = ResourceModel(
        idRecursos,
        idRecursoServidor,
        email,
        password,
        idEspecie,
        ubicacion,
        alturaUbicacion,
        idCategoria,
        imagen,
        fechaAlta,
        latitud,
        longitud,
        idCalle,
        idEstadoRecurso,
        ultimaActualizacion
    )
}