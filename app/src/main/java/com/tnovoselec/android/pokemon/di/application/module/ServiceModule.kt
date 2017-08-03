package com.tnovoselec.android.pokemon.di.application.module

import com.tnovoselec.android.pokemon.BuildConfig
import com.tnovoselec.android.pokemon.data.service.PokemonApi
import com.tnovoselec.android.pokemon.data.service.PokemonService
import com.tnovoselec.android.pokemon.data.service.PokemonServiceImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ServiceModule {

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkhttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            builder.interceptors().add(interceptor)
        }

        return builder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl("http://pokeapi.co/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun providePokemonService(pokemonApi: PokemonApi): PokemonService {
        return PokemonServiceImpl(pokemonApi)
    }

    @Provides
    @Singleton
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    interface Exposes {

        val pokemonService: PokemonService

    }
}