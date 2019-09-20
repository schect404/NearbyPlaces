package com.atitto.data.places.repository

import com.atitto.data.places.PlacesApi
import com.atitto.data.places.model.toNearByPlaces
import com.atitto.domain.places.PlacesRepository
import com.atitto.domain.places.model.NearByPlace
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

class PlacesRepositoryImpl(private val placesApi: PlacesApi): PlacesRepository {

    override fun getPlaces(location: LatLng): Single<List<NearByPlace>> = placesApi.getNearbyPlaces("${location.latitude},${location.longitude}").map { it.toNearByPlaces() }

}