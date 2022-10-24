package com.example.myapplication.domain.useCase

import com.example.myapplication.common.DataState
import com.example.myapplication.data.dto.GiphyDto
import com.example.myapplication.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetGiphyUseCase @Inject constructor(
    private val repository: GiphyRepository
) {
    // operator fun invoke(): Flow<DataState<GiphyDto>> = flow {
    fun getGiphy(): Flow<DataState<GiphyDto>> = flow {
        try {
            emit(DataState(loading = true))
            val giphy = repository.getGiphy()
            emit(DataState(data = giphy))
        } catch (e: Exception) {
            val error = DataState<GiphyDto>(exception = "Unable to get giphy response: ${e.message}")
            emit(error)
        } catch (cause: Throwable) {
            val error = DataState<GiphyDto>(exception = "Unable to get giphy response: ${cause.message}")
            emit(error)
        }
    }
}
