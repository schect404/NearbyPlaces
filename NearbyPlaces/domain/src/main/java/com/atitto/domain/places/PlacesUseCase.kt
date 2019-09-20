package com.atitto.domain.places

import com.atitto.domain.places.model.NearByPlace
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface PlacesUseCase {
    fun getPlaces(location: LatLng): Single<List<NearByPlace>>
}

class PlacesUseCaseImpl(private val placesRepository: PlacesRepository): PlacesUseCase {

    override fun getPlaces(location: LatLng) = placesRepository.getPlaces(location)
}