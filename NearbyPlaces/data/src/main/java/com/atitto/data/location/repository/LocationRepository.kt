package com.atitto.data.location.repository

import android.location.Location
import com.atitto.data.location.LocationProvider
import com.atitto.data.location.LocationProviderImpl
import com.atitto.domain.location.LocationRepository

class LocationRepositoryImpl(private val locationProvider: LocationProvider): LocationRepository {

    override fun requestLocation(callback: (Location?) -> Unit) = locationProvider.requestLocation(callback)

}