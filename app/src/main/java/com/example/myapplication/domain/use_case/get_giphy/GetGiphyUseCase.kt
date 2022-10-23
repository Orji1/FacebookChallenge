package com.example.myapplication.domain.use_case.get_giphy

import com.example.myapplication.common.DataState
import com.example.myapplication.common.Resource
import com.example.myapplication.data.dto.GiphyDto
import com.example.myapplication.data.dto.toGiphy
import com.example.myapplication.domain.model.Giphy
import com.example.myapplication.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGiphyUseCase @Inject constructor(
    private val repository: GiphyRepository
) {
    operator fun invoke(): Flow<DataState<GiphyDto>> = flow {
            emit(DataState(loading = true))
            val giphy = repository.getGiphy()
            emit(DataState(data = giphy))
        /*} catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred"))

        } catch (e: IOException) {
            emit(Resource.Error(message = "Unable to retrieve giphy information from server"))
        }*/
    }
}