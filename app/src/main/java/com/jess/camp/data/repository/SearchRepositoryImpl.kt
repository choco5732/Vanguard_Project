package com.jess.camp.data.repository

import com.jess.camp.data.remote.SearchRemoteDatasource
import com.jess.camp.domain.model.ImageDocumentEntity
import com.jess.camp.domain.model.SearchEntity
import com.jess.camp.domain.model.toImageEntity
import com.jess.camp.domain.repository.SearchRepository
// 도메인레이어가 알아먹을 수 있는 형태로 만들어야 함
class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDatasource
) : SearchRepository {
    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int
    ): SearchEntity<ImageDocumentEntity> = remoteDataSource.getSearchImage(
            query,
            sort,
            page,
            size
    ).toImageEntity()
}