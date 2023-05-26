package com.example.billysweatherapp.features.shortWaveRaditaionFeature.data.workmanager

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class UploadWorker(appContext: Context, workerParams: WorkerParameters):
    Worker(appContext, workerParams)
{
    override fun doWork(): Result {
        //work to be done in the worker
        return Result.success (
            workDataOf("imageUri" to "string.toUri") // for passing data up to ui
        ) // return a result state, success, failure, retry
    }
}

val uploadWorkRequest: WorkRequest = PeriodicWorkRequestBuilder<UploadWorker>(
    15,
    TimeUnit.HOURS,
    15,
    TimeUnit.MINUTES
)
    .build()



fun work (context: Context){
    WorkManager
        .getInstance(context)
        .enqueue(uploadWorkRequest)
}
