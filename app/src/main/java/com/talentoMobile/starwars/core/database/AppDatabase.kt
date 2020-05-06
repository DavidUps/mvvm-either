package com.davidups.starwars.core.dataBase

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.davidups.starwars.core.dao.PersonDAO
import com.davidups.starwars.features.people.models.PersonEntity
import com.talentoMobile.starwars.core.dao.FetchDateDAO
import com.talentoMobile.starwars.features.fetch.models.FetchEntity

@Database(entities = [PersonEntity::class, FetchEntity::class], version = 2)
abstract class AppDatabase: RoomDatabase() {

    abstract fun personEntityDao(): PersonDAO
    abstract fun fetchEntityDao(): FetchDateDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        AppDatabase::class.java,
                        "starWarsDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}