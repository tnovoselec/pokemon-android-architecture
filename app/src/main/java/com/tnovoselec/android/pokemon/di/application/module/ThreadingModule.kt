package com.tnovoselec.android.pokemon.di.application.module

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named
import javax.inject.Singleton


@Module
class ThreadingModule {

    @Provides
    @Singleton
    @Named(ThreadingModule.MAIN_SCHEDULER)
    fun provideMainScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }

    interface Exposes {

        @Named(ThreadingModule.MAIN_SCHEDULER)
        fun mainScheduler(): Scheduler
    }

    companion object {

        const val MAIN_SCHEDULER = "main_scheduler"
    }
}
