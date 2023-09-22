package com.jess.camp.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.camp.domain.usecase.GetSearchImageUseCase
import com.jess.camp.domain.usecase.GetSearchVideoUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchImage: GetSearchImageUseCase,
    private val searchVideo: GetSearchVideoUseCase
) : ViewModel() {

    fun search(query: String) = viewModelScope.launch {
        runCatching {
            val images = searchImage(query)
            val videos = searchVideo(query)
            Log.d("jess", images.toString())
            Log.d("jess", videos.toString())
        }.onFailure {
            Log.e("jess", it.message.toString())
        }
    }
}
