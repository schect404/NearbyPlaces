package com.atitto.data.places

import com.atitto.data.BuildConfig
import com.atitto.data.places.model.ApiPlaces
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApi {

    @GET("place/nearbysearch/json")
    fun getNearbyPlaces(@Query("location") location: String,
                        @Query("radius") proximityRadius: Int = 500,
                        @Query("sensor") sensor: Boolean = true,
                        @Query("type") type: String = "restaurant",
                        @Query("key") apiKey: String = BuildConfig.API_KEY): Single<ApiPlaces>

}