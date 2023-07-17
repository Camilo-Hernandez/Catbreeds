package com.camihruiz24.catbreeds.core

import com.camihruiz24.catbreeds.data.ItemsDataSource
import com.camihruiz24.catbreeds.data.ItemsDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun provideItemDataSource(impl: ItemsDataSourceImpl) : ItemsDataSource
}