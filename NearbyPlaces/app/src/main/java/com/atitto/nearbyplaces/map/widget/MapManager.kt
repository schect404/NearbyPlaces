package com.atitto.nearbyplaces.map.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.atitto.domain.places.model.NearByPlace
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import java.util.concurrent.atomic.AtomicReference

interface MapManager {

    fun initMap(mapFragment: SupportMapFragment, callback: () -> Unit)
    fun showNearByPlaces(places: List<NearByPlace>)
    fun releaseMap()

}

class MapManagerImpl(private val context: Context): MapManager {

    private val map = AtomicReference<GoogleMap>(null)

    private fun attachMap(map: GoogleMap) {
        this.map.set(map)
    }

    override fun releaseMap() {
        map.set(null)
    }

    override fun initMap(mapFragment: SupportMapFragment, callback: () -> Unit) =
        mapFragment.getMapAsync {
            it.apply {
                mapType = GoogleMap.MAP_TYPE_NORMAL
                clear()
                isMyLocationEnabled = true
            }.also { map ->
                attachMap(map)
                callback.invoke()
                mapFragment.context?.let {
                    val markerWindowAdapter = CustomMarkerInfoView(it)
                    map.setInfoWindowAdapter(markerWindowAdapter)
                }
            }
        }

    override fun showNearByPlaces(places: List<NearByPlace>) {
        map.get()?.clear()
        places.forEach { addMarker(it) }
    }

    private fun addMarker(place: NearByPlace) {
        val options = MarkerOptions()
            .position(place.location)

        map.get()?.addMarker(options)?.tag = place
    }

}