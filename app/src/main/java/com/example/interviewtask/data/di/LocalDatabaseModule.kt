package com.example.interviewtask.data.di

import android.app.Application
import androidx.room.Room
import com.example.interviewtask.data.local.dao.FeedsDao
import com.example.interviewtask.data.local.database.StreamsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application): StreamsDatabase =
        Room.databaseBuilder(
            application,
            StreamsDatabase::class.java,
            "streams_database"
        ).build()

    @Provides
    @Singleton
    fun provideFeedsDao(database: StreamsDatabase): FeedsDao =
        database.feedsDao()
}