package com.fikrisandi.domain.di

import com.fikrisandi.domain.news.GetListNews
import com.fikrisandi.repository.repository.news.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideGetListNews(repository: NewsRepository) = GetListNews(repository)


}