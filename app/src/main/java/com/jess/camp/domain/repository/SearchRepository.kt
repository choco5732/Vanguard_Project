package com.jess.camp.domain.repository

import com.jess.camp.domain.model.ImageDocumentEntity
import com.jess.camp.domain.model.SearchEntity
import com.jess.camp.domain.model.VideoDocumentEntity

interface SearchRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchEntity<ImageDocumentEntity>

    suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchEntity<VideoDocumentEntity>

}