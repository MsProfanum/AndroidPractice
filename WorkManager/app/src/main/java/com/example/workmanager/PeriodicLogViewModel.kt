package com.example.workmanager

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.util.UUID

class PeriodicLogViewModel : ViewModel() {
    var log: String? by mutableStateOf(null)
        private set
    var logResult: String? by mutableStateOf(null)
        private set

    var workId: UUID? by mutableStateOf(null)
        private set

    fun updateLog(log: String?) {
        this.log = log
    }

    fun updateLogResult(result: String?) {
        logResult = result
    }

    fun updateWorkId(id: UUID?) {
        workId = id
    }
}