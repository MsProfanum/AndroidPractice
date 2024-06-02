package com.example.workmanager

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf

var res: String? = null

class PeriodicLogWorker(
    private val appContext: Context,
    private val params: WorkerParameters,
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {
        val log = params.inputData.getString(LOG_TAG)
        val timestamp = System.currentTimeMillis()
        val result = "$log has timestamp: $timestamp"

        res = result
        
        setProgress(
            workDataOf(
                RESULT to result
            )
        )

        return Result.success(
            workDataOf(
                LOG_TAG to result
            )
        )
    }

    companion object {
        const val LOG_TAG = "LOG_TAG"
        const val RESULT = "RESULT"
    }
}