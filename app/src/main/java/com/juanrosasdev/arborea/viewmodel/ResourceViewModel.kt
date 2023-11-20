package com.juanrosasdev.arborea.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanrosasdev.arborea.data.repository.ResourceRepository
import com.juanrosasdev.arborea.data.model.ResourceModel
import kotlinx.coroutines.launch

class ResourceViewModel : ViewModel() {
    val resourceModel = MutableLiveData<ResourceModel>()
    val isLoading = MutableLiveData<Boolean>()


    var resourceRepository = ResourceRepository()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = resourceRepository.getAllResources()
            if (!result.isNullOrEmpty()){
                resourceModel.postValue(result[0])
                isLoading.postValue(false)

            }
        }
    }


}