package com.juanrosasdev.arborea.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.juanrosasdev.arborea.databinding.ActivityMainBinding
import com.juanrosasdev.arborea.ui.viewmodel.ResourceViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val resourceViewModel: ResourceViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        resourceViewModel.onCreate()

        resourceViewModel.resourceModel.observe(this, Observer {
            val resourceFromService = "Recurso id ${it.idRecursoServidor} :: latitud ${it.latitud} :: longitud ${it.longitud} "
            binding.txttest.text = resourceFromService

        })

        resourceViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })
    }

}