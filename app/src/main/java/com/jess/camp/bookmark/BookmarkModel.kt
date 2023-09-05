package com.jess.camp.bookmark

data class BookmarkModel(
    val title: String?,
    val description: String?,
    val isBookmark: Boolean = false
)