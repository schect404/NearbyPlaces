package com.atitto.domain.places.model

import com.google.android.gms.maps.model.LatLng

data class NearByPlace(
    val location: LatLng,
    val name: String,
    val vicinity: String,
    val icon: String
)