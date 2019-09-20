package com.atitto.data.places.model

import com.atitto.domain.places.model.NearByPlace
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

fun ApiPlace.toNearByPlace() = NearByPlace(
    name = name,
    vicinity = vicinity,
    icon = icon,
    location = LatLng(geometry.location.lat, geometry.location.lng)
)

fun ApiPlaces.toNearByPlaces() = results.map { it.toNearByPlace() }

data class ApiPlaces (
    @SerializedName("results")
    val results: List<ApiPlace>
)

data class ApiPlace (
    @SerializedName("geometry")
    val geometry: ApiGeometry,
    @SerializedName("name")
    val name: String,
    @SerializedName("vicinity")
    val vicinity: String,
    @SerializedName("icon")
    val icon: String
)

data class ApiGeometry (
    @SerializedName("location")
    val location: ApiLocation
)

data class ApiLocation (
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)
