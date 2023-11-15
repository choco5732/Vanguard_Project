package com.jess.camp.presentation.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.camp.domain.usecase.GetSearchImageUseCase
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchImage: GetSearchImageUseCase
) : ViewModel() {
    fun search(query: String) {
        viewModelScope.launch {
            val list = searchImage(query)
            Log.d("choco5732", list.toString())
        }
    }

    init {

        }
}