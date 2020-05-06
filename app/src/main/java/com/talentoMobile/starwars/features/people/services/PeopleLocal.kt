package com.davidups.starwars.features.people.services

import com.davidups.starwars.core.dataBase.AppDatabase
import com.davidups.starwars.core.platform.ContextHandler
import com.davidups.starwars.features.people.models.PersonEntity

class PeopleLocal (contextHandler: ContextHandler): PeopleDBLocal {

    private val peopleApi by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.personEntityDao()
    }

    override fun getPeople(): List<PersonEntity> = peopleApi!!.getPeople()
    override fun addPeople(personEntity: List<PersonEntity>) = peopleApi!!.insertPeople(personEntity)
}