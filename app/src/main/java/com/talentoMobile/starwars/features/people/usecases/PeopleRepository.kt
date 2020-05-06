package com.davidups.starwars.features.people.usecases

import android.content.SharedPreferences
import com.davidups.starwars.core.exception.Failure
import com.davidups.starwars.core.functional.Either
import com.davidups.starwars.core.platform.NetworkHandler
import com.davidups.starwars.core.platform.ServiceKOs
import com.davidups.starwars.features.people.models.PeopleEntity
import com.davidups.starwars.features.people.models.Person
import com.davidups.starwars.features.people.models.PersonEntity
import com.davidups.starwars.features.people.services.PeopleLocal
import com.davidups.starwars.features.people.services.PeopleService
import com.talentoMobile.starwars.features.fetch.models.FetchEntity
import com.talentoMobile.starwars.features.fetch.services.FetchLocal
import retrofit2.Call
import java.lang.Exception
import java.util.*

interface PeopleRepository {

    fun getPeople(): Either<Failure, List<Person>>
    fun getRemotePeople(): Either<Failure, List<Person>>

    class Network
        (
        private val networkHandler: NetworkHandler,
        private val service: PeopleService,
        private val local: PeopleLocal,
        private val fetch: FetchLocal
    ) : PeopleRepository {

        override fun getPeople(): Either<Failure, List<Person>> {
            return try {
                val people: List<PersonEntity>? = local.getPeople()
                val fetchDate: FetchEntity? = fetch.getFetchDate(0)
                if (people.isNullOrEmpty() || fetchDate == null || isFetchCurrentNeeded(fetchDate.fetchData)) {
                    getRemotePeople()
                } else {
                    Either.Right(local.getPeople().map {
                        it.toPerson()
                    })
                }
            } catch (e: Exception) {
                Either.Left(Failure.CustomError(ServiceKOs.DATABASE_ACCESS_ERROR, e.message))
            }
        }

        override fun getRemotePeople(): Either<Failure, List<Person>> {

            return when (networkHandler.isConnected) {
                true -> request(
                    service.getPeople(),
                    {
                        val people: List<PersonEntity> = it.results
                        addPeople(people)

                        fetch.addFetchDate(FetchEntity(0, Date().time))

                        people.map { personEntity ->
                            personEntity.toPerson()
                        }
                    },
                    PeopleEntity.isEmpty()
                )
                false, null -> Either.Left(Failure.NetworkConnection())
            }
        }

        private fun addPeople(people: List<PersonEntity>) {
            local.addPeople(people)
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError())
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError())
            }
        }

        private fun isFetchCurrentNeeded(lastFetchTime: Long): Boolean {
            val oneMinuteInMillis = 60000
            val thirtyMinutesAgo = Date(Date().time - (30 * oneMinuteInMillis)).time
            return Date(lastFetchTime).before(Date(thirtyMinutesAgo))
        }
    }
}