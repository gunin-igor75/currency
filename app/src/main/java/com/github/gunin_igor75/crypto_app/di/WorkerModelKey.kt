package com.github.gunin_igor75.crypto_app.di

import androidx.work.ListenableWorker
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class WorkerModelKey(val value: KClass<out ListenableWorker>)
