package com.atitto.data

import com.atitto.data.location.LocationProvider
import com.atitto.data.location.LocationProviderImpl
import com.atitto.data.location.repository.LocationRepositoryImpl
import com.atitto.data.places.PlacesApi
import com.atitto.data.places.repository.PlacesRepositoryImpl
import com.atitto.data.retrofit.RetrofitFactory
import com.atitto.data.retrofit.RetrofitFactoryImpl
import com.atitto.domain.location.LocationRepository
import com.atitto.domain.places.PlacesRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val dataModule = Kodein.Module {
    bind<Gson>() with singleton { GsonBuilder().setLenient().create() }
    bind<OkHttpClient>() with singleton { OkHttpClient() }
    bind<RetrofitFactory>() with singleton { RetrofitFactoryImpl() }
    bind<Retrofit>() with singleton { instance<RetrofitFactory>().createRetrofit(instance(), instance()) }
    bind<LocationProvider>() with singleton { LocationProviderImpl(instance()) }
    bind<LocationRepository>() with singleton { LocationRepositoryImpl(instance()) }
    bind<PlacesApi>() with singleton { instance<Retrofit>().create(PlacesApi::class.java) }
    bind<PlacesRepository>() with singleton { PlacesRepositoryImpl(instance()) }
}