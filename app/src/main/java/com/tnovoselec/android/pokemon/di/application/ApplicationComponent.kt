package com.tnovoselec.android.pokemon.di.application

import com.tnovoselec.android.pokemon.di.application.module.*
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        ApplicationModule::class,
        ThreadingModule::class,
        UtilsModule::class,
        UseCaseModule::class,
        DataModule::class,
        ServiceModule::class,
        MappersModule::class))
interface ApplicationComponent : ApplicationComponentInjects, ApplicationComponentExposes {

    object Initializer {

        fun init(pokemonApplication: PokemonApplication): ApplicationComponent {
            return DaggerApplicationComponent.builder()
                    .applicationModule(ApplicationModule(pokemonApplication))
                    .threadingModule(ThreadingModule())
                    .utilsModule(UtilsModule())
                    .useCaseModule(UseCaseModule())
                    .dataModule(DataModule())
                    .serviceModule(ServiceModule())
                    .mappersModule(MappersModule())
                    .build()
        }
    }
}
