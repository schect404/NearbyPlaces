package com.atitto.domain.places

import com.atitto.domain.places.model.NearByPlace
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Single

interface PlacesRepository {
    fun getPlaces(location: LatLng): Single<List<NearByPlace>>
}