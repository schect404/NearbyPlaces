package com.atitto.nearbyplaces.base

import android.app.Application
import android.content.Context
import com.atitto.data.dataModule
import com.atitto.domain.domainModule
import com.atitto.nearbyplaces.di.appModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class App: Application(), KodeinAware {

    override val kodein: Kodein = Kodein {
        import(appModule)
        import(dataModule)
        import(domainModule)
        bind<Context>() with provider { this@App }
    }

}