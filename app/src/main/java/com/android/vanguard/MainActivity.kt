package com.android.vanguard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.vanguard.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뷰페이저 설정
        val adapter = FragmentAdapter(this)
        val fragments = listOf<Fragment>(TodoFragment(), BookmarkFragment())
        adapter.fragments.addAll(fragments)

         val tabTitles =  listOf<String>("todo", "bookmark")

        // 뷰페이저 연결
        // 뷰바인딩 처리 해줘야할듯
        binding.viewPager.adapter = adapter

        // 탭레이아웃과 뷰페이저 연결
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

    }
}

// 뷰페지어 어댑터

class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
    val fragments = mutableListOf<Fragment>()
    // 뷰의 갯수
    override fun getItemCount(): Int {
        return fragments.size
    }

    // 현재 페이지의 프래그먼트를 전달
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}