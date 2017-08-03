package com.tnovoselec.android.pokemon.di.application

import com.tnovoselec.android.pokemon.di.application.module.*


interface ApplicationComponentExposes : ApplicationModule.Exposes, ThreadingModule.Exposes, UtilsModule.Exposes, UseCaseModule.Exposes, DataModule.Exposes, MappersModule.Exposes
