package com.juanrosasdev.arborea.data.network

import com.juanrosasdev.arborea.core.RetrofitHelper
import com.juanrosasdev.arborea.data.model.ResourceModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResourceService {
    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getResources(): List<ResourceModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getAllResources()
            response.body() ?: emptyList()
        }
    }

}