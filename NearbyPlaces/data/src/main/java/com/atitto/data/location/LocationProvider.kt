package com.atitto.data.location

import android.content.Context
import android.location.*
import com.google.android.gms.location.*

interface LocationProvider {
    fun requestLocation(callback:(Location?) -> Unit)
}

class LocationProviderImpl(private val context: Context): LocationProvider {

    override fun requestLocation(callback: (Location?) -> Unit) {
        val locationRequest = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(100000000)
            .setFastestInterval(100000000)

        val locationCallback = object: LocationCallback() {
            override fun onLocationResult(result: LocationResult?) {
                super.onLocationResult(result)
                callback.invoke(result?.locations?.first())
            }

            override fun onLocationAvailability(p0: LocationAvailability?) {
                super.onLocationAvailability(p0)
                if(p0?.isLocationAvailable == false) callback.invoke(null)
            }
        }

        LocationServices.getFusedLocationProviderClient(context).requestLocationUpdates(locationRequest, locationCallback, null)
    }

}