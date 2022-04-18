package com.itis.foody.common.di.modules

import com.itis.foody.features.signup.data.repositories.SignUpRepositoryImpl
import com.itis.foody.features.signup.domain.repositories.SignUpRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun bindsSignUpRepository(
        impl: SignUpRepositoryImpl
    ): SignUpRepository
}
