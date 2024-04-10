package com.josemiz.globantcodechallenge.data

import com.josemiz.globantcodechallenge.data.remote.connection.EventsConnection
import com.josemiz.globantcodechallenge.data.repository.EventsRepository
import com.josemiz.globantcodechallenge.data.repository.EventsRepositoryImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModules {

    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Singleton
    @Provides
    fun providesHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(httpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://app.ticketmaster.com/discovery/v2/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)
            .build()
    }

    @Singleton
    @Provides
    fun providesEventsConnection(retrofit: Retrofit): EventsConnection {
        return retrofit.create(EventsConnection::class.java)
    }

    @Singleton
    @Provides
    fun providesEventsRepository(eventsConnection: EventsConnection): EventsRepository {
        return EventsRepositoryImpl(eventsConnection)
    }
}