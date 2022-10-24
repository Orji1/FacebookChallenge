package com.example.myapplication.di

import com.example.myapplication.common.Constants
import com.example.myapplication.data.remote.GiphyApi
import com.example.myapplication.data.repository.GiphyRepositoryImpl
import com.example.myapplication.domain.repository.GiphyRepository
import com.example.myapplication.domain.useCase.getGiphy.GetGiphyUseCase
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
    fun provideGiphyApi(): GiphyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GiphyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGiphyRepository(api: GiphyApi): GiphyRepository {
        return GiphyRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGiphyUseCase(repository: GiphyRepository): GetGiphyUseCase {
        return GetGiphyUseCase(repository)
    }
}
