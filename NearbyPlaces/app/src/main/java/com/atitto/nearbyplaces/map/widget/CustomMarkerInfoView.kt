package com.atitto.nearbyplaces.map.widget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.atitto.domain.places.model.NearByPlace
import com.atitto.nearbyplaces.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.marker_info_window.view.*


class CustomMarkerInfoView(private val context: Context): GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(marker: Marker?): View? = null

    override fun getInfoWindow(marker: Marker?): View {
        val layout = LayoutInflater.from(context).inflate(R.layout.marker_info_window, null)
        val place = marker?.tag as? NearByPlace ?: return layout
        layout.tvAddress.text = place.vicinity
        layout.tvName.text = place.name
        Glide.with(context).load(place.icon).into(layout.ivIcon)
        return layout
    }

}