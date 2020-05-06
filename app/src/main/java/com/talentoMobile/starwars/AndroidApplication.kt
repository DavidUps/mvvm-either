package com.davidups.starwars

import android.app.Application
import com.davidups.starwars.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AndroidApplication)
            modules(listOf(
                networkModule,
                applicationModule,
                databaseModule,
                dataSourceModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            ))
        }
    }
}
