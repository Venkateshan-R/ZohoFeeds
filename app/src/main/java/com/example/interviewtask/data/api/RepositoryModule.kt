package com.example.interviewtask.data.api

import com.example.interviewtask.data.repository.FeedsRepository
import com.example.interviewtask.data.repository.FeedsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFeedsRepository(feedsRepositoryImpl: FeedsRepositoryImpl): FeedsRepository

}