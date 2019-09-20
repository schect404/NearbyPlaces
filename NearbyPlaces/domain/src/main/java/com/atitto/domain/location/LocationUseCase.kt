package com.atitto.domain.location

import android.location.Location

interface LocationUseCase {

    fun requestLocation(callback: (Location?) -> Unit)

}

class LocationUseCaseImpl(private val locationRepository: LocationRepository): LocationUseCase {

    override fun requestLocation(callback: (Location?) -> Unit) = locationRepository.requestLocation(callback)

}