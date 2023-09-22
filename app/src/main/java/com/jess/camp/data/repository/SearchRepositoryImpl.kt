package com.jess.camp.data.repository

import com.jess.camp.data.remote.SearchRemoteDatasource
import com.jess.camp.domain.model.ImageDocumentEntity
import com.jess.camp.domain.model.SearchEntity
import com.jess.camp.domain.model.VideoDocumentEntity
import com.jess.camp.domain.model.toEntity
import com.jess.camp.domain.model.toImageEntity
import com.jess.camp.domain.model.toVideoEntity
import com.jess.camp.domain.repository.SearchRepository

class SearchRepositoryImpl(
    private val remoteDatasource: SearchRemoteDatasource
) : SearchRepository {

    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): SearchEntity<ImageDocumentEntity> = remoteDatasource.getSearchImage(
        query,
        sort,
        page,
        size
    ).toImageEntity()

    override suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): SearchEntity<VideoDocumentEntity> = remoteDatasource.getSearchVideo(
        query
    ).toVideoEntity()
}