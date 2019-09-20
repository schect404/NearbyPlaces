package com.atitto.nearbyplaces.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atitto.domain.location.LocationUseCase
import com.atitto.domain.places.PlacesUseCase
import com.atitto.domain.places.model.NearByPlace
import com.atitto.nearbyplaces.common.makeAction
import com.atitto.nearbyplaces.common.makeActionAndConsume
import com.google.android.gms.maps.model.LatLng
import io.reactivex.disposables.CompositeDisposable
import rx.exceptions.CompositeException

interface MapViewModel {

    val errorLiveData: LiveData<String>
    val nearByPlacesLiveData: LiveData<List<NearByPlace>>

    fun init()

}

class MapViewModelImpl(private val locationUseCase: LocationUseCase,
                       private val placesUseCase: PlacesUseCase) : ViewModel(), MapViewModel {

    override val errorLiveData: MutableLiveData<String> = MutableLiveData()

    override val nearByPlacesLiveData: MutableLiveData<List<NearByPlace>> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    override fun init() {
        locationUseCase.requestLocation { it?.let { compositeDisposable.makeActionAndConsume(placesUseCase.getPlaces(LatLng(it.latitude, it.longitude)), errorLiveData, nearByPlacesLiveData) } }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
