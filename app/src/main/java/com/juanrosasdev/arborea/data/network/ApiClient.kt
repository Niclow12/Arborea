package com.juanrosasdev.arborea.data.network

import com.juanrosasdev.arborea.data.model.ResourceModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

    @GET("/.json")
    suspend fun getAllResources(): Response<List<ResourceModel>>

}