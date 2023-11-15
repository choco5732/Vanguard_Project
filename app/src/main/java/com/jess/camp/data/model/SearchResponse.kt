package com.jess.camp.data.model

import com.google.gson.annotations.SerializedName
import java.util.Date


// 서버개발자가 파일을 누락하는 경우를 대비해 타입을 nullable로 해야 앱이 죽지않는다.
data class SearchResponse<T> (
        @SerializedName("meta") val meta: MetaResponse?,
        @SerializedName("documents") val documents: List<T>?
)

data class MetaResponse(
        @SerializedName("total_count") val totalCount: Int?,
        @SerializedName("pageable_count") val pageableCount: Int?,
        @SerializedName("is_end") val isEnd: Boolean?
)

data class ImageDocumentResponse(
        @SerializedName("collection") val collection: String?,
        @SerializedName("thumbnail_url") val thumbnailUrl: String?,
        @SerializedName("image_url") val imageUrl: String?,
        @SerializedName("width") val width: Int?,
        @SerializedName("height") val height: Int?,
        @SerializedName("display_sitename") val displaySitename: String?,
        @SerializedName("doc_url") val docUrl: String?,
        @SerializedName("datetime") val datetime: Date?
)

data class VideoDocumentResponse(
        @SerializedName("title") val title: String?,
        @SerializedName("url") val url: String?,
        @SerializedName("datetime") val datetime: Date?,
        @SerializedName("play_time") val playTime: Int?,
        @SerializedName("thumbnail") val thumbNail: String?,
        @SerializedName("author") val author: String?
)