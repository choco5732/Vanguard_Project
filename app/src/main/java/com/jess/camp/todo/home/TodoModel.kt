package com.jess.camp.todo.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoModel(
    val id: Long? = null,
    val title: String?,
    val description: String?
) : Parcelable