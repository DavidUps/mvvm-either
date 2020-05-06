package com.davidups.starwars.features.people.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.davidups.starwars.core.extensions.empty
import com.google.gson.annotations.SerializedName

@Entity
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "birth_year")
    @SerializedName("birth_year")
    val birthYear: String?,

    @SerializedName("eye_color")
    @ColumnInfo(name = "eye_color")
    val eyeColor: String?,

    @ColumnInfo(name = "gender")
    val gender: String?,

    @SerializedName("hair_color")
    @ColumnInfo(name = "hair_color")
    val hairColor: String?,

    @ColumnInfo(name = "height")
    val height: Int?,

    @ColumnInfo(name = "homeworld")
    val homeworld: String?,

    @ColumnInfo(name = "mass")
    val mass: Int?,

    @ColumnInfo(name = "name")
    val name: String?,

    @SerializedName("skin_color")
    @ColumnInfo(name = "skin_color")
    val skinColor: String

    ) {
    companion object {
        fun empty() = PersonEntity(0,
            "",
            "",
            "",
            "",
            0,
            "",
            0,
            "",
            "")
    }

    fun toPerson(): Person {
        return Person(
            birthYear?:"",
            eyeColor?:"",
            gender?:"",
            hairColor?:"",
            height?:0,
            homeworld?:"",
            mass?:0,
            name?:"",
            skinColor?:""
            )
    }
}