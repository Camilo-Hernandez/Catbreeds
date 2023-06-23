package com.camihruiz24.catbreeds.core

import com.camihruiz24.catbreeds.data.CatBreedsRepository
import com.camihruiz24.catbreeds.data.CatBreedsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    // Yo te doy la implementación de la interfaz, tú me das la interfaz
    // Esto es útil para las pruebas, ya que podemos implementar la interfaz en una clase de prueba
    @Binds
    @Singleton
    abstract fun bindCatsRepository(catBreedsRepositoryImpl: CatBreedsRepositoryImpl) : CatBreedsRepository

}
