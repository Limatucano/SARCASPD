package com.br.faeterj.paracambi.sarcaspd.util

import android.Manifest
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.ActivityCompat
import java.lang.Exception

open class LocationProvider(
    private val context : Context
) : Service(), LocationListener {

    private lateinit var locationManager : LocationManager
    private var location : Location? = null

    init {
        if(getLocationByNetwork() == null){
            getLocationByGps()
        }
    }

    fun getLatitude() : Double {
        if(location != null){
            return location!!.latitude
        }
        return 0.0
    }

    fun getLongitude() : Double {
        if(location != null){
            return location!!.longitude
        }
        return 0.0
    }

    private fun getLocation(provider : String) : Location?{
        try {
            locationManager = context.getSystemService(LOCATION_SERVICE) as LocationManager
            val checkProvider = locationManager.isProviderEnabled(provider)

            if(checkProvider && checkPermission()){
                locationManager.requestLocationUpdates(
                    provider,
                    TIME_UPDATES_MS,
                    TIME_DISTANCE_CHANGE_FOR_UPDATES,
                    this
                )
                location = locationManager.getLastKnownLocation(provider)
            }

        }catch (e: Exception){
            return null
        }
        return location
    }

    private fun getLocationByGps() : Location?{
        return getLocation(LocationManager.GPS_PROVIDER)
    }

    private fun getLocationByNetwork() : Location?{
        return getLocation(LocationManager.NETWORK_PROVIDER)
    }

    private fun checkPermission() : Boolean{
        if(
           ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED  &&
           ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED
        ){
            return false
        }
        return true
    }
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onLocationChanged(location: Location) {}

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}

    override fun onProviderDisabled(provider: String) {
        super.onProviderDisabled(provider)
    }

    override fun onProviderEnabled(provider: String) {
        super.onProviderEnabled(provider)
    }


    companion object{
        const val PERMISSION_GRANTED = PackageManager.PERMISSION_GRANTED
        const val TIME_UPDATES_MS = 1000 * 60 * 1L //1 min
        const val TIME_DISTANCE_CHANGE_FOR_UPDATES = 10F
    }
}