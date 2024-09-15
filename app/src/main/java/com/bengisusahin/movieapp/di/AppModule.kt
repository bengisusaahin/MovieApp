package com.bengisusahin.movieapp.di

import com.bengisusahin.movieapp.data.remote.MovieAPI
import com.bengisusahin.movieapp.data.repository.MovieRepositoryImpl
import com.bengisusahin.movieapp.domain.repository.MovieRepository
import com.bengisusahin.movieapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: MovieAPI): MovieRepository {
        return MovieRepositoryImpl(api)
    }

}