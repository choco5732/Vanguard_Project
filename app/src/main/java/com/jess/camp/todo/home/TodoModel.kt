package com.jess.camp.todo.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoModel(
    var title: String?,
    var description: String?
) : Parcelable
