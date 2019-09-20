package com.atitto.nearbyplaces.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.atitto.nearbyplaces.R
import com.atitto.nearbyplaces.common.PermissionManager
import com.atitto.nearbyplaces.common.bindDataTo
import com.atitto.nearbyplaces.map.widget.MapManager
import com.google.android.gms.maps.SupportMapFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

class MapFragment : Fragment(), KodeinAware {

    override val kodein: Kodein = Kodein.lazy {
        val parentKodein: Kodein by closestKodein()
        extend(parentKodein, allowOverride = true)
        bind<MapViewModel>() with provider { MapViewModelImpl(instance(), instance()) }
    }

    private val viewModel: MapViewModel by kodein.instance()

    private val permissionManager: PermissionManager by kodein.instance()

    private val mapManager: MapManager by kodein.instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.map_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        knowLocation()
        bindViewModel()
    }

    private fun initMap() {
        val map = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        map?.let {
            mapManager.initMap(it) {
                viewModel.init()
            }
        }
    }

    private fun bindViewModel() {
        bindDataTo(viewModel.nearByPlacesLiveData, mapManager::showNearByPlaces)
        bindDataTo(viewModel.errorLiveData) { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(permissionManager.isLocationPermissionGranted()) initMap() else permissionManager.requestPermission(this)
    }

    private fun knowLocation() =
        if (permissionManager.isLocationPermissionGranted()) permissionManager.requestPermission(this) else initMap()

    override fun onDestroy() {
        super.onDestroy()
        mapManager.releaseMap()
    }

    companion object {
        fun newInstance() = MapFragment()
    }

}
