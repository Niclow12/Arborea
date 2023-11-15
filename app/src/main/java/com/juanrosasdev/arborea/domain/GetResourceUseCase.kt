package com.juanrosasdev.arborea.domain

import com.juanrosasdev.arborea.data.ResourceRepository

class GetResourceUseCase {
    private val repository = ResourceRepository()

    suspend operator fun invoke() =repository.getAllResources()

}