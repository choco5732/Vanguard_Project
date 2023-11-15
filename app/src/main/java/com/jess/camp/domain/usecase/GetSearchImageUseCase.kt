package com.jess.camp.domain.usecase

import com.jess.camp.domain.repository.SearchRepository

// usecase는 단 하나의 기능만을 위한 클래스를 말한다.
// data레이어에 있는 SearchRepositoryImpl를 domain레이어가 알아먹을 수 있는 형태로 바꿔야한다.
class GetSearchImageUseCase(
    private val repository: SearchRepository
) {
    // suspend operator fun ??!
    suspend operator fun invoke(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80
    ) = repository.getSearchImage(
            query,
            sort,
            page,
            size
    )
}