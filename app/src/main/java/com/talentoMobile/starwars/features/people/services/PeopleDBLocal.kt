package com.davidups.starwars.features.people.services

import com.davidups.starwars.core.platform.ContextHandler
import com.davidups.starwars.features.people.models.PersonEntity

interface PeopleDBLocal {

    fun getPeople(): List<PersonEntity>
    fun addPeople(personEntity: List<PersonEntity>): Any
}
