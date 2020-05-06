package com.davidups.starwars.features.people.services

import com.davidups.starwars.features.people.models.PeopleEntity
import com.davidups.starwars.features.people.models.PersonEntity
import retrofit2.Call
import retrofit2.http.GET

internal interface PeopleApi {

    companion object {
        private const val PEOPLE = "people"
    }

    @GET(PEOPLE)
    fun getPeople(): Call<PeopleEntity>
}