package com.atitto.domain.location

import android.location.Location

interface LocationRepository {

    fun requestLocation(callback: (Location?) -> Unit)

}