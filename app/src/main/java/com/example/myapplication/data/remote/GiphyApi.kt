package com.example.myapplication.data.remote

import com.example.myapplication.data.dto.GiphyDto
import retrofit2.http.GET

interface GiphyApi {
    @GET("v1/gifs/trending?api_key=EEjeWKnay8eNwJ091mC2ffGuQe96tdBN")
    suspend fun getGiphy():GiphyDto
}