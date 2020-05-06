package com.davidups.starwars.core.di

import android.content.Context
import android.content.SharedPreferences
import com.davidups.starwars.BuildConfig
import com.davidups.starwars.core.platform.ContextHandler
import com.davidups.starwars.core.platform.NetworkHandler
import com.davidups.starwars.features.people.services.PeopleLocal
import com.davidups.starwars.features.people.services.PeopleService
import com.davidups.starwars.features.people.usecases.GetPeople
import com.davidups.starwars.features.people.usecases.PeopleRepository
import com.davidups.starwars.features.people.views.viewmodels.GetPeopleViewModel
import com.davidups.starwars.features.people.views.fragments.PeopleFragment
import com.davidups.starwars.features.people.views.adapters.PeopleAdapter
import com.talentoMobile.starwars.features.fetch.services.FetchLocal
import com.talentoMobile.starwars.features.people.workers.GetPepoleWorker
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { ContextHandler(get()) }
    factory { NetworkHandler(get()) }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.ENVIRONMENT_URL)
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //Si necesitamos el builder para proporcionarle otra urlbase
    single {
        Retrofit.Builder()
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
    }
}
val applicationModule = module(override = true) {
    scope(named<PeopleFragment>()) {
        factory { PeopleAdapter() }
    }

    factory { GetPepoleWorker(get(), get()) }

    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            BuildConfig.APPLICATION_ID + "-" + BuildConfig.ENVIRONMENT,
            Context.MODE_PRIVATE
        )
    }
}

val useCaseModule = module {
    factory { GetPeople(get()) }
}

val repositoryModule = module {
    factory<PeopleRepository> { PeopleRepository.Network(get(), get(), get(), get()) }
}

val dataSourceModule = module {
    factory { PeopleService(get()) }
}

val databaseModule = module {
    factory { PeopleLocal(get()) }
    factory { FetchLocal(get()) }
}

val viewModelModule = module {
    viewModel {
        GetPeopleViewModel(get())
    }
}

private fun createClient(): OkHttpClient {
    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(loggingInterceptor)
    }
    return okHttpClientBuilder.build()
}
