package com.itis.foody.common.di.modules

import android.content.Context
import com.itis.foody.common.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.invoke(context) as AppDatabase

    @Provides
    fun provideUserDao(database: AppDatabase) = database.userDao()

    @Provides
    fun provideRecipeSetDao(database: AppDatabase) = database.recipeSetDao()

    @Provides
    fun provideRecipeDao(database: AppDatabase) = database.recipeDao()
}
