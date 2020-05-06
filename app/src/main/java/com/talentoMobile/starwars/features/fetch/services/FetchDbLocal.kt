package com.talentoMobile.starwars.features.fetch.services

import com.talentoMobile.starwars.features.fetch.models.FetchEntity

interface FetchDbLocal {
    fun getFetchDate(id: Int): FetchEntity
    fun addFetchDate(fetchEntity: FetchEntity): Any
}