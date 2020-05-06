package com.davidups.starwars.features.people.models

import com.davidups.starwars.core.extensions.empty

data class PeopleEntity (val count: Int, val next: String, val previus: String, val results: ArrayList<PersonEntity> ) {
    companion object {
        fun isEmpty() = PeopleEntity(Int.empty(), String.empty(), String.empty(), arrayListOf())
    }
}