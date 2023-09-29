package com.jess.camp.presentation.search

data class SearchUiState(
    val list: List<SearchItem>,
    val isLoading: Boolean
) {

    companion object {

        fun initialize() = SearchUiState(
            list = emptyList(),
            isLoading = false
        )
    }
}