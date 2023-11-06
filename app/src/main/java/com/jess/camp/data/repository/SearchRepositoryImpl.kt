package com.jess.camp.data.repository

import com.jess.camp.data.remote.SearchRemoteDatasource
import com.jess.camp.domain.repository.SeachRepository

class SearchRepositoryImpl(
    private val remoteDataSource: SearchRemoteDatasource
) : SeachRepository {
}