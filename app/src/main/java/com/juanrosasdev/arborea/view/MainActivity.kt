package com.juanrosasdev.arborea.view

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.juanrosasdev.arborea.R
import com.juanrosasdev.arborea.data.model.ResourceModelBuilder
import com.juanrosasdev.arborea.data.network.LocationService
import com.juanrosasdev.arborea.databinding.ActivityMainBinding
import com.juanrosasdev.arborea.viewmodel.ResourceViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val resourceViewModel: ResourceViewModel by viewModels()
    private lateinit var locationService: LocationService
    private var latitud: String? = null
    private var longitud: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        locationService = LocationService(this)
        setUI()

        initializeLocation()
        initializeResourceViewModel()
        val resourceModel = latitud?.let {
            longitud?.let { it1 ->
                ResourceModelBuilder()
                    .idRecursos(1)
                    .idRecursoServidor(2)
                    .latitud(it)
                    .longitud(it1)
                    .email("example@example.com")
                    .build()
            }
        }
        if (resourceModel != null) {
            Toast.makeText(this, "lat ${resourceModel.latitud}", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setUI() {
        binding.btnViewmap.setOnClickListener {
            navigateToViewMapActivity()
        }

        binding.btnNewResource.setOnClickListener {
            navigateToNewResourceActivity()
        }


    }
    private fun navigateToViewMapActivity() {
        val intent = Intent(this, MapResourceActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToNewResourceActivity() {
        val intent = Intent(this, FormResourceActivity::class.java)
        startActivity(intent)
    }

    private fun initializeResourceViewModel() {
        resourceViewModel.onCreate()

        resourceViewModel.resourceModel.observe(this, Observer { resource ->
            val resourceText =
                "Recurso id ${resource.idRecursoServidor} :: latitud ${resource.latitud} :: longitud ${resource.longitud} "
            binding.txttest.text = resourceText
        })

        resourceViewModel.isLoading.observe(this, Observer { isLoading ->
            binding.loading.isVisible = isLoading
        })
    }

    private fun initializeLocation() {
        binding.txtlocation.text = "esperando ubicacion"

        lifecycleScope.launchWhenStarted {
            val userLocation = locationService.getUserLocation(binding.txtlocation)
            if (userLocation != null) {
                updateLocationUI(userLocation)
            } else {
                showLocationError("no se encontro ubicacion")
            }
        }
    }

    private fun updateLocationUI(locationResult: Location?) {
        this.latitud = locationResult?.latitude.toString()
        this.longitud = locationResult?.longitude.toString()
        val locationText =
            "Ubicación: Latitud ${locationResult?.latitude}, " +
                    "Longitud ${locationResult?.longitude} " +
                    "Accuracy: ${locationResult?.accuracy}"
        binding.txtlocation.text = locationText
    }

    private fun showLocationError(errorMessage: String) {
        binding.txtlocation.text = getString(R.string.error_with_parameter, errorMessage)
    }
}
