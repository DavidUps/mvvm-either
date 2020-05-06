package com.davidups.starwars.features.people.views

import android.app.Person
import com.davidups.starwars.core.extensions.empty
import java.io.Serializable

data class PersonView(
    val birthYear: String,
    val eyeColor: String,
    val gender: String,
    val hairColor: String,
    val height: Int,
    val homeworld: String,
    val mass: Int,
    val name: String,
    val skinColor: String
) : Serializable {
    companion object {
        fun empty(): PersonView = PersonView(
            String.empty(),
            String.empty(),
            String.empty(),
            String.empty(),
            Int.empty(),
            String.empty(),
            Int.empty(),
            String.empty(),
            String.empty()
        )
    }
}