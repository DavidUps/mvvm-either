package com.davidups.starwars.core.dao

import androidx.room.*
import com.davidups.starwars.features.people.models.PeopleEntity
import com.davidups.starwars.features.people.models.PersonEntity

@Dao
interface PersonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPeople(personEntity: List<PersonEntity>)

    @Update
    fun updatePeople(personEntity: PersonEntity)

    @Delete
    fun deletePeople(personEntity: PersonEntity)

    @Query("SELECT * FROM PersonEntity")
    fun getPeople(): List<PersonEntity>

    @Query("SELECT * FROM PersonEntity WHERE id == :peopleId")
    fun getPeopleById(peopleId: Int): List<PersonEntity>
}