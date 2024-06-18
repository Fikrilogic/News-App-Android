package com.fikrisandi.repository.di

import com.fikrisandi.framework.network.AppHttpClient
import com.fikrisandi.repository.repository.user.UserRepository
import com.fikrisandi.repository.repository.user.UserRepositoryImpl
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
    fun provideUserRepository(appClient: AppHttpClient): UserRepository = UserRepositoryImpl(appClient)
}