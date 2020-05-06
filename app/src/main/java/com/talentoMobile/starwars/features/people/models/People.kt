package com.davidups.starwars.features.people.models

import com.davidups.starwars.core.extensions.empty

data class People(val count: Int, val next: String, val previus: String, val results: ArrayList<Person>) {
    companion object {
        fun isEmpty() = People(Int.empty(), String.empty(), String.empty(), arrayListOf())
    }
}