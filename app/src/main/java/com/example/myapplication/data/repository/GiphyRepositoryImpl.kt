package com.example.myapplication.data.repository

import com.example.myapplication.data.remote.GiphyApi
import com.example.myapplication.domain.repository.GiphyRepository
import javax.inject.Inject

class GiphyRepositoryImpl @Inject constructor(
    private val giphyApi: GiphyApi
): GiphyRepository {
    override suspend fun getGiphy() = giphyApi.getGiphy()
}