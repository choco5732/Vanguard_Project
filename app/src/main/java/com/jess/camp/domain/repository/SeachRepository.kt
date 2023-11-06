package com.jess.camp.domain.repository

interface SeachRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    )
}