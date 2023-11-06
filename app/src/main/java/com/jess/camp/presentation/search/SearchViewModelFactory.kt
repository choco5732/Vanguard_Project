package com.jess.camp.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            refutn SearchViewModel(

            ) as T
        } else {
            throw IllegalArgumentException("Not found Viewmodel class.")
        }
    }
}