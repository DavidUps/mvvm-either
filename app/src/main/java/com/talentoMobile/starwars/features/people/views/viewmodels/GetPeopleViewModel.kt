package com.davidups.starwars.features.people.views.viewmodels

import androidx.lifecycle.MutableLiveData
import com.davidups.starwars.core.interactor.UseCase
import com.davidups.starwars.core.platform.BaseViewModel
import com.davidups.starwars.features.people.models.Person
import com.davidups.starwars.features.people.usecases.GetPeople
import com.davidups.starwars.features.people.views.PersonView

class GetPeopleViewModel
    (private val getPeople: GetPeople): BaseViewModel() {

    var person: MutableLiveData<List<PersonView>> = MutableLiveData()

    fun getPeople() = getPeople(UseCase.None()){
        it.either(::handleFailure, ::handlePersonList)
    }

    fun handlePersonList(people: List<Person>) {
        this.person.value = people.map {
            it.toPersonView()
        }.toMutableList()
    }
}

