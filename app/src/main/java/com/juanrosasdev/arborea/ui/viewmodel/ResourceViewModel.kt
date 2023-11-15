package com.juanrosasdev.arborea.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juanrosasdev.arborea.data.model.ResourceModel
import com.juanrosasdev.arborea.domain.GetResourceUseCase
import kotlinx.coroutines.launch

class ResourceViewModel : ViewModel() {
    val resourceModel = MutableLiveData<ResourceModel>()
    val isLoading = MutableLiveData<Boolean>()


    var getResourceUseCase = GetResourceUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getResourceUseCase()
            if (!result.isNullOrEmpty()){
                resourceModel.postValue(result[0])
                isLoading.postValue(false)

            }


        }
    }


}