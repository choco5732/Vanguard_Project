package com.jess.camp.domain.model

import java.util.Date

data class SearchEntity<T>(
    val meta: MetaEntity?,
    val documents: List<T>?
)

data class MetaEntity(
    val totalCount: Int?,
    val pageableCount: Int?,
    val isEnd: Boolean?
)

data class ImageDocumentEntity(
    val collection: String?,
    val thumbnailUrl: String?,
    val imageUrl: String?,
    val width: Int?,
    val height: Int?,
    val displaySitename: String?,
    val docUrl: String?,
    val datetime: Date?,
)

data class VideoDocumentEntity(
    val title: String?,
    val url: String?,
    val playTime: String?,
    val thumbnail: String?,
    val author: String?,
    val datetime: Date?,
)