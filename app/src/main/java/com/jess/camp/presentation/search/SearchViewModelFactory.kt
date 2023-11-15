package com.jess.camp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jess.camp.data.repository.SearchRepositoryImpl
import com.jess.camp.domain.repository.SearchRepository
import com.jess.camp.domain.usecase.GetSearchImageUseCase
import com.jess.camp.retrofit.RetrofitClient

class SearchViewModelFactory : ViewModelProvider.Factory {

    private val repository : SearchRepository = SearchRepositoryImpl(
        RetrofitClient.search
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                GetSearchImageUseCase(repository)

            ) as T
        } else {
            throw IllegalArgumentException("Not found Viewmodel class.")
        }
    }
}