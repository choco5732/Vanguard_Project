package com.jess.camp.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jess.camp.bookmark.BookmarkModel
import com.jess.camp.todo.home.TodoModel
import com.jess.camp.todo.home.toBookmarkModel

// TodoFragment와 BookmarkFragment를 교환할 수 있는 메인 뷰모델
class MainSharedViewModel : ViewModel() {

    private val _event: MutableLiveData<MainSharedEvent> = MutableLiveData()
    val event: LiveData<MainSharedEvent> get() = _event

    // 받아온 TodoModel을 BookMark가 알아먹을 수 있도록 해줘야함.
    fun updateTodoItems(list: List<TodoModel>?) {
        // TodoModel 리스트 중에 Bookmark된 것만 필터링 해주고 그것을 bookmarkmodel로 컨버팅 해주는 작업.
        val bookmarkItems = list?.filter {
            it.isBookmark
        }?.map{
            it.toBookmarkModel()
        }.orEmpty() // orEmpty()는 만약 list가 null이나 empty일 경우 [ ] 비어있는 리스트를 반환해주는 녀석이다.

          _event.value = MainSharedEvent.UpdateBookmarkItems(bookmarkItems)
    }
}

// 이벤트를 처리를 할 수 있는 sealed Interface
// 데이터를 저장하지 않고 일회성으로 데이터를 소비하는 형태를 sealed class로 만듬.
sealed interface MainSharedEvent {
    data class  UpdateBookmarkItems(
        val items: List<BookmarkModel>
    ) : MainSharedEvent
}