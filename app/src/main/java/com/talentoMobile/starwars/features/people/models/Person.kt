package com.davidups.starwars.features.people.models

import com.davidups.starwars.core.extensions.empty
import com.davidups.starwars.features.people.views.PersonView

data class Person(
    val birthYear: String,
    val eyeColor: String,
    val gender: String,
    val hairColor: String,
    val height: Int,
    val homeworld: String,
    val mass: Int,
    val name: String,
    val skinColor: String
) {

    companion object {
        fun empty() = Person(
            String.empty(), String.empty(), String.empty(), String.empty(),
            Int.empty(), String.empty(), Int.empty(), String.empty(), String.empty()
        )
    }

    fun toPersonView() = PersonView(
        birthYear,
        eyeColor,
        gender,
        hairColor,
        height,
        homeworld,
        mass,
        name,
        skinColor
    )

    fun toPersonEntity() = PersonEntity(
        0,
        birthYear,
        eyeColor,
        gender,
        hairColor,
        height,
        homeworld,
        mass,
        name,
        skinColor
    )
}