package com.jess.camp.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jess.camp.R
import com.jess.camp.bookmark.BookmarkFragment
import com.jess.camp.todo.home.TodoFragment

class MainViewPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        TodoFragment.newInstance() to R.string.main_tab_todo_title,
        BookmarkFragment.newInstance() to R.string.main_tab_bookmark_title,
    )

    fun getFragment(position: Int): Fragment {
        return fragments[position].first
    }

    fun getTodoFragment(): TodoFragment? {
        return fragments[0].first as? TodoFragment
    }

    fun getTitle(position: Int): Int {
        return fragments[position].second
    }

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position].first
    }
}