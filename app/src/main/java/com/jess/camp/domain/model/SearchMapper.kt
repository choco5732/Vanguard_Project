package com.jess.camp.domain.model

import com.jess.camp.data.model.ImageDocumentResponse
import com.jess.camp.data.model.MetaResponse
import com.jess.camp.data.model.SearchResponse
import com.jess.camp.data.model.VideoDocumentResponse


fun SearchResponse<ImageDocumentResponse>.toImageEntity() = SearchEntity<ImageDocumentEntity>(
    meta = meta?.toEntity(),
    documents = documents?.map { response ->
        response.toEntity()
    }
)

fun SearchResponse<VideoDocumentResponse>.toVideoEntity() = SearchEntity<VideoDocumentEntity>(
    meta = meta?.toEntity(),
    documents = documents?.map { response ->
        response.toEntity()
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

fun VideoDocumentResponse.toEntity() = VideoDocumentEntity(
    title = title,
    url = url,
    playTime = playTime,
    thumbnail = thumbnail,
    author = author,
    datetime = datetime
)