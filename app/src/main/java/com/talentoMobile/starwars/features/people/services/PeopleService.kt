package com.davidups.starwars.features.people.services

import com.davidups.starwars.features.people.models.PeopleEntity
import com.davidups.starwars.features.people.models.PersonEntity
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
class PeopleService (retrofit: Retrofit): PeopleApi {

    private val peopleApi by lazy { retrofit.create(PeopleApi::class.java) }

    override fun getPeople(): Call<PeopleEntity> {
        return peopleApi.getPeople()
    }
}