package com.atitto.domain

import com.atitto.domain.location.LocationUseCase
import com.atitto.domain.location.LocationUseCaseImpl
import com.atitto.domain.places.PlacesUseCase
import com.atitto.domain.places.PlacesUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val domainModule = Kodein.Module {
    bind<LocationUseCase>() with provider { LocationUseCaseImpl(instance()) }
    bind<PlacesUseCase>() with provider { PlacesUseCaseImpl(instance()) }
}
