package com.jess.camp.todo.home

import android.os.Parcelable
import com.jess.camp.bookmark.BookmarkModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoModel(
    val title: String?,
    val description: String?,
    val isBookmark: Boolean = false
) : Parcelable

fun TodoModel.toBookmarkModel(): BookmarkModel {
    return BookmarkModel(
        title = title,
        description = description,
        isBookmark = isBookmark
    )
}