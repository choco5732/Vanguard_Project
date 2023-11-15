package com.jess.camp.domain.model

import com.jess.camp.data.model.ImageDocumentResponse
import com.jess.camp.data.model.MetaResponse
import com.jess.camp.data.model.SearchResponse

// 함수명으로 접근하는 법?
fun SearchResponse<ImageDocumentResponse>.toImageEntity() = SearchEntity<ImageDocumentEntity>(
    meta = meta?.toEntity(),
    documents = documents?.map {
        it.toEntity()
    }
)

fun MetaResponse.toEntity() = MetaEntity(
    totalCount = totalCount,
    pageableCount = pageableCount,
    isEnd = isEnd
)

fun ImageDocumentResponse.toEntity() = ImageDocumentEntity(
    collection = collection,
    thumbnailUrl = thumbnailUrl,
    imageUrl = imageUrl,
    width = width,
    height = height,
    displaySitename = displaySitename,
    docUrl = docUrl,
    datetime = datetime
)