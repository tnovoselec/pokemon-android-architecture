package com.tnovoselec.android.pokemon.di.application.module

import android.content.Context
import com.tnovoselec.android.pokemon.data.util.connectivity.*
import com.tnovoselec.android.pokemon.di.application.ForApplication
import com.tnovoselec.android.pokemon.ui.util.ActivityUtils
import com.tnovoselec.android.pokemon.ui.util.ActivityUtilsImpl
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


@Module
class UtilsModule {

//    @Provides
//    @Singleton
//    internal fun provideCollectionUtils(): CollectionUtils {
//        return CollectionUtilsImpl()
//    }
//
    @Provides
    @Singleton
    internal fun provideActivityUtils(): ActivityUtils {
        return ActivityUtilsImpl()
    }
//
//    @Provides
//    @Singleton
//    internal fun provideCurrentTimeProvider(): CurrentTimeProvider {
//        return CurrentTimeProviderImpl()
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideDateUtils(): DateUtils {
//        return DateUtilsImpl()
//    }
//
    @Provides
    @Singleton
    internal fun provideConnectivityReceiver(@ForApplication context: Context, networkUtils: NetworkUtils): ConnectivityReceiver {
        return ConnectivityReceiverImpl(context, networkUtils, Schedulers.io())
    }

    @Provides
    @Singleton
    internal fun provideNetworkUtils(connectivityManagerWrapper: ConnectivityManagerWrapper): NetworkUtils {
        return NetworkUtilsImpl(connectivityManagerWrapper)
    }
//
    @Provides
    @Singleton
    internal fun provideConnectivityManagerWrapper(@ForApplication context: Context): ConnectivityManagerWrapper {
        return ConnectivityManagerWrapperImpl(context)
    }
//
//    @Provides
//    @Singleton
//    internal fun provideNotificationUtils(@ForApplication context: Context): NotificationUtils {
//        return NotificationUtilsImpl(context)
//    }
//
//    @Provides
//    @Singleton
//    internal fun provideImageLoader(@ForApplication context: Context): ImageLoader {
//        return ImageLoaderImpl(context)
//    }
//
//    @Provides
//    @Singleton
//    internal fun providePreferenceUtils(@ForApplication context: Context): PreferenceUtils {
//        return PreferenceUtilsImpl(context)
//    }
//
    interface Exposes {
//
//        fun collectionUtils(): CollectionUtils
//
        fun activityUtils(): ActivityUtils
//
//        fun currentTimeProvider(): CurrentTimeProvider
//
//        fun dateUtils(): DateUtils
//
        fun connectivityReceiver(): ConnectivityReceiver
//
//        fun notificationUtils(): NotificationUtils
//
//        fun imageLoader(): ImageLoader
    }
}
