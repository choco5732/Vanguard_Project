package com.jess.camp.presentation.search

sealed interface SearchEvent {

    data class OpenDetail(
        val item: SearchItem
    ) : SearchEvent
}