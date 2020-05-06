package com.talentoMobile.starwars.features.fetch.services

import com.davidups.starwars.core.dataBase.AppDatabase
import com.davidups.starwars.core.platform.ContextHandler
import com.talentoMobile.starwars.features.fetch.models.FetchEntity

class FetchLocal
(contextHandler: ContextHandler): FetchDbLocal {

    private val fetchDb by lazy {
        AppDatabase.getAppDataBase(contextHandler.appContext)?.fetchEntityDao()!!
    }

    override fun getFetchDate(id: Int): FetchEntity = fetchDb.getFetchDataById(id)
    override fun addFetchDate(fetchEntity: FetchEntity) = fetchDb.insertFetchDate(fetchEntity)
}