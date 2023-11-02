package com.fikrisandi.repository.di

import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.repository.repository.news.NewsRepository
import com.fikrisandi.repository.repository.news.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideNewsRepository(appClient: AppHttpClient): NewsRepository =
        NewsRepositoryImpl(appClient)

}