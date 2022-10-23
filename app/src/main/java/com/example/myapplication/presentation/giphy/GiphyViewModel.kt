package com.example.myapplication.presentation.giphy

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.DataState
import com.example.myapplication.common.Resource
import com.example.myapplication.data.dto.GiphyDto
import com.example.myapplication.domain.use_case.get_giphy.GetGiphyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val getGiphyUseCase: GetGiphyUseCase
) : ViewModel() {

    /*private val _state = mutableStateOf(GiphyState())
    val giphyState : State<GiphyState> = _state*/

    private val _stateFlow: MutableStateFlow<DataState<GiphyDto>> = MutableStateFlow(DataState(loading = true))
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch {getGiphy()}

    }

    /*private fun getGiphy() {
        getGiphyUseCase().onEach { result ->
            when(result){
                is Resource.Success -> {
                    _state.value = GiphyState(giphy = result.data ?: GiphyDto())
                }
                is Resource.Error -> {
                    _state.value = GiphyState(error = result.message ?: "An Unexpected error occurred")
                }
                is Resource.Loading -> {
                    _state.value = GiphyState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }*/

    private suspend fun getGiphy() {
        flowOf(
            getGiphyUseCase()
        ).flattenMerge().collect { dataState ->
            if (dataState.loading) {
                val temp = _stateFlow.value.copy(loading = true)
                _stateFlow.value = temp
            } else {
                _stateFlow.value = dataState
                Log.v("looking for data", "$dataState")
            }
        }

    }
}