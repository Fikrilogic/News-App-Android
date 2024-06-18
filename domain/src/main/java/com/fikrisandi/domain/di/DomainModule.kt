package com.fikrisandi.domain.di

import com.fikrisandi.domain.user.GetListUser
import com.fikrisandi.domain.user.GetUserByUsername
import com.fikrisandi.repository.repository.user.UserRepository
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
    fun provideGetListUser(repository: UserRepository) = GetListUser(repository)

    @Provides
    @Singleton
    fun provideGetUserByUsername(repository: UserRepository) = GetUserByUsername(repository)
}