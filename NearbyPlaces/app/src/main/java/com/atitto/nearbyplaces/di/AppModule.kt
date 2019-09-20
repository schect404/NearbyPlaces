package com.atitto.nearbyplaces.di

import com.atitto.nearbyplaces.common.PermissionManager
import com.atitto.nearbyplaces.common.PermissionManagerImpl
import com.atitto.nearbyplaces.map.widget.MapManager
import com.atitto.nearbyplaces.map.widget.MapManagerImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val appModule = Kodein.Module {
    bind<PermissionManager>() with provider { PermissionManagerImpl(instance()) }
    bind<MapManager>() with provider { MapManagerImpl(instance()) }
}