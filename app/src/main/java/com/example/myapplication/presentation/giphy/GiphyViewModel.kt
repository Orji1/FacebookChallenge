package com.example.myapplication.presentation.giphy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.common.DataState
import com.example.myapplication.data.dto.GiphyDto
import com.example.myapplication.domain.useCase.GetGiphyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GiphyViewModel @Inject constructor(
    private val getGiphyUseCase: GetGiphyUseCase
) : ViewModel() {
    private val _stateFlow: MutableStateFlow<DataState<GiphyDto>> = MutableStateFlow(DataState(loading = true))
    val stateFlow = _stateFlow.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) { getGiphy() }
    }

    private suspend fun getGiphy() {
        flowOf(
            getGiphyUseCase.getGiphy()
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
