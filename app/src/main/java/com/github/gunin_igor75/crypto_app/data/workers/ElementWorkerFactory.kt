package com.github.gunin_igor75.crypto_app.data.workers

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerParameters

interface ElementWorkerFactory {

    fun create(
        context: Context,
        workerParameters: WorkerParameters
    ):ListenableWorker
}