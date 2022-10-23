package com.example.myapplication.domain.repository

import com.example.myapplication.data.dto.GiphyDto

interface GiphyRepository {
    suspend fun getGiphy():GiphyDto
}