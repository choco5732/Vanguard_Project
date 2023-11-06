package com.jess.camp.data.remote

import com.jess.camp.data.model.ImageDocumentResponse
import com.jess.camp.data.model.SearchResponse
import com.jess.camp.data.model.VideoDocumentResponse
import retrofit2.http.GET
import retrofit2.http.Query

// 구글이 사용하는 표현 RemoteDataSource
interface SearchRemoteDatasource {

    @GET("/v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : SearchResponse<ImageDocumentResponse>

    @GET("/v2/search/vclip")
    suspend fun getSearchVideo(
        @Query("query") query: String,
        @Query("sort") sort: String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ) : SearchResponse<VideoDocumentResponse>

}