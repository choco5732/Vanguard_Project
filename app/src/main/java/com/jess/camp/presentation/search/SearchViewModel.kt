package com.jess.camp.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.camp.domain.model.ImageDocumentEntity
import com.jess.camp.domain.model.SearchEntity
import com.jess.camp.domain.model.VideoDocumentEntity
import com.jess.camp.domain.usecase.GetSearchImageUseCase
import com.jess.camp.domain.usecase.GetSearchVideoUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchImage: GetSearchImageUseCase,
    private val searchVideo: GetSearchVideoUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState.initialize())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun search(
        query: String
    ) = viewModelScope.launch {
        runCatching {
            val items = createItems(
                images = searchImage(query),
                videos = searchVideo(query)
            )

            _uiState.update { prevState ->
                prevState.copy(
                    list = items
                )
            }
        }.onFailure {
            // network, error, ...
            Log.e("jess", it.message.toString())
        }
    }

    private fun createItems(
        images: SearchEntity<ImageDocumentEntity>,
        videos: SearchEntity<VideoDocumentEntity>
    ): List<SearchItem> {

        fun createImageItems(
            images: SearchEntity<ImageDocumentEntity>
        ): List<SearchItem.ImageItem> = images.documents?.map { document ->
            SearchItem.ImageItem(
                title = document.displaySitename,
                thumbnail = document.thumbnailUrl,
                date = document.datetime
            )
        }.orEmpty()

        fun createVideoItems(
            videos: SearchEntity<VideoDocumentEntity>
        ): List<SearchItem.VideoItem> = videos.documents?.map { document ->
            SearchItem.VideoItem(
                title = document.title,
                thumbnail = document.thumbnail,
                date = document.datetime
            )
        }.orEmpty()

        // merge
        val items = arrayListOf<SearchItem>().apply {
            addAll(createImageItems(images))
            addAll(createVideoItems(videos))
        }

        // sort
        items.sortByDescending {
            it.date
        }

        return items
    }
}
