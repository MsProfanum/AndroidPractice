package com.example.workmanager

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.workmanager.ui.theme.WorkManagerTheme
import java.util.concurrent.TimeUnit

class SecondActivity : ComponentActivity() {
    private lateinit var workManager: WorkManager
    private val viewModel by viewModels<PeriodicLogViewModel>()
    private val log: String = "HEJECZKA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        workManager = WorkManager.getInstance(applicationContext)
        viewModel.updateLog(log)

        val request = PeriodicWorkRequestBuilder<PeriodicLogWorker>(15, TimeUnit.MINUTES)
            .setInputData(
                workDataOf(
                    PeriodicLogWorker.LOG_TAG to log
                )
            )
            .build()

        viewModel.updateWorkId(request.id)

//        workManager.enqueueUniquePeriodicWork(
//            request.id.toString(),
//            ExistingPeriodicWorkPolicy.UPDATE,
//            request
//        )
        workManager.enqueue(request)

        setContent {
            WorkManagerTheme {
                workManager.getWorkInfoByIdLiveData(request.id).observeForever { workerResult ->
                    viewModel.updateLogResult(res)

                    if (workerResult != null) {
                        Log.d(TAG, "WorkInfo received: state: " + workerResult.state)
                        Log.d(
                            TAG,
                            "WorkInfo received: progress result: " + workerResult.progress.getString(
                                PeriodicLogWorker.RESULT
                            )
                        )

//                        viewModel.updateLogResult(progress)
                    }
                }


                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = "Second Activity with log: ${viewModel.log}")
                    Spacer(modifier = Modifier.height(16.dp))
                    viewModel.logResult?.let {
                        Text(text = it)
                    }
                }
            }
        }
    }
}