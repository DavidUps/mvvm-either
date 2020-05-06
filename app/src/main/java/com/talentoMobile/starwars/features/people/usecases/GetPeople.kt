package com.davidups.starwars.features.people.usecases

import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.features.people.models.Person

class GetPeople (private val peopleRepository: PeopleRepository): UseCase<List<Person>, UseCase.None>() {
    override suspend fun run(params: None) = peopleRepository.getPeople()
}