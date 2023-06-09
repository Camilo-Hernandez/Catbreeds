package com.camihruiz24.catbreeds.core

import com.camihruiz24.catbreeds.feature_list.data.CatsRepository
import com.camihruiz24.catbreeds.feature_list.data.CatsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Yo te doy la implementación de la interfaz, tú me das la interfaz
    // Esto es útil para las pruebas, ya que podemos implementar la interfaz en una clase de prueba
    @Binds
    abstract fun bindCatsRepository(catsRepositoryImpl: CatsRepositoryImpl) : CatsRepository

}