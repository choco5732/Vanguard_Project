package com.jess.camp.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jess.camp.R
import com.jess.camp.presentation.bookmark.BookmarkFragment
import com.jess.camp.presentation.search.SearchFragment
import com.jess.camp.presentation.todo.home.TodoFragment

class MainViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf<MainTabs>(
        MainTabs(TodoFragment.newInstance(), R.string.main_tab_todo_title),
        MainTabs(BookmarkFragment.newInstance(), R.string.main_tab_bookmark_title),
        MainTabs(SearchFragment.newInstance(), R.string.main_tab_search_title)
    )


    fun getFragment(position: Int): Fragment {
        return fragments[position].fragment
    }

    fun getTitle(position: Int): Int {
        return fragments[position].titleRes
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].fragment
    }
}