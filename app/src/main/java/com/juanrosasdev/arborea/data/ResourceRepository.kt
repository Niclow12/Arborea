package com.juanrosasdev.arborea.data

import com.juanrosasdev.arborea.data.model.ResourceModel
import com.juanrosasdev.arborea.data.model.ResourceProvider
import com.juanrosasdev.arborea.data.network.ResourceService

class ResourceRepository {
    private val api = ResourceService()
    suspend fun getAllResources(): List<ResourceModel> {
        val response = api.getResources()
        ResourceProvider.resource = response
        return response
    }
}