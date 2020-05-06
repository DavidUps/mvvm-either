package com.talentoMobile.starwars.features.people.workers

import android.content.Context
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.davidups.starwars.core.extensions.Constants
import com.davidups.starwars.features.people.usecases.PeopleRepository
import org.koin.core.KoinComponent
import org.koin.core.inject


class GetPepoleWorker
constructor(context: Context, params: WorkerParameters): Worker(context,params), KoinComponent {

    val peopleRepository: PeopleRepository by inject()

    override fun doWork(): Result = try {
        Thread.sleep(3000)
        peopleRepository.getPeople()
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(applicationContext, notification)
        r.play()

        Result.success()
    } catch (e: Throwable) {
        Log.e(Constants.LOG_ERROR_TAG, "Error executing work: " + e.message, e)
        Result.failure()
    }
}