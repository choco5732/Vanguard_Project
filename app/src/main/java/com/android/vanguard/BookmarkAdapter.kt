package com.android.vanguard

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.vanguard.databinding.FragmentBookmarkItemBinding

// bookmark 리사이클러뷰 어댑터

class BookmarkAdapter(val list : List<Bookmark>) : RecyclerView.Adapter<Holder2>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder2 {

        val binding = FragmentBookmarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder2(binding)
    }

    override fun onBindViewHolder(holder: Holder2, position: Int) {
        val bookmark = list[position]
        holder.setItem(bookmark)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class Holder2(private val binding: FragmentBookmarkItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun setItem(bookmark : Bookmark){
        binding.textView.text = "${bookmark.text}"
    }
}