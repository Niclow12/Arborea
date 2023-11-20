package com.juanrosasdev.arborea.data.network

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.location.LocationProvider
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocationService(private val context: Context) {
    private var locationManager: LocationManager? = null
    private var locationListener: LocationListener? = null

    suspend fun getUserLocation(textView: TextView): Location? {
        return withContext(Dispatchers.Main) {
            locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            locationListener = object : LocationListener {
                override fun onLocationChanged(location: Location) {
                    // Puedes manejar la ubicación actualizada aquí
                    val locationText =
                        "Actualizacion: Latitud ${location.latitude}, " +
                                "Longitud ${location.longitude} " +
                                "Accuracy: ${location.accuracy}"
                    textView.text = locationText
                    Log.e("TAG", "onLocationChanged: $locationText", )
                }

                override fun onProviderDisabled(provider: String) {
                    textView.text = "GPS Desactivado"

                    // Puedes manejar la lógica cuando el proveedor de ubicación está desactivado
                }

                override fun onProviderEnabled(provider: String) {
                    textView.text = "GPS Activado"

                    // Puedes manejar la lógica cuando el proveedor de ubicación está activado
                }

                override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    when (status) {
                        LocationProvider.AVAILABLE -> Log.d("debug", "LocationProvider.AVAILABLE")
                        LocationProvider.OUT_OF_SERVICE -> Log.d(
                            "debug",
                            "LocationProvider.OUT_OF_SERVICE"
                        )

                        LocationProvider.TEMPORARILY_UNAVAILABLE -> Log.d(
                            "debug",
                            "LocationProvider.TEMPORARILY_UNAVAILABLE"
                        )
                    }

                    // Puedes manejar la lógica cuando el estado del proveedor de ubicación cambia
                }
            }

            // Puedes ajustar el proveedor y los criterios según tus necesidades
            val provider = LocationManager.GPS_PROVIDER
            val minTime: Long = 500 // Intervalo de tiempo en milisegundos
            val minDistance = 3f // Distancia en metros

            if (context.checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                locationManager?.requestLocationUpdates(provider, minTime, minDistance,
                    locationListener as LocationListener
                )
                val lastKnownLocation = locationListener?.let { locationManager?.getLastKnownLocation(provider) }
                lastKnownLocation?.let {
                    locationManager?.removeUpdates(locationListener as LocationListener)
                    return@withContext it
                }
            }

            return@withContext null
        }
    }

    fun stopLocationUpdates() {
        locationListener?.let { locationManager?.removeUpdates(it) }
    }
}