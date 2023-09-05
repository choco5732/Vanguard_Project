package com.jess.camp.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jess.camp.databinding.BookmarkItemBinding

class BookmarkListAdapter(
    private val onBookmarkChecked: (Int, BookmarkModel) -> Unit
) : RecyclerView.Adapter<BookmarkListAdapter.ViewHolder>() {

    private val list = ArrayList<BookmarkModel>()

    fun addItem(item: BookmarkModel?) {
        item?.let {
            list.add(item)
            notifyItemChanged(list.size - 1)
        }
    }

    fun addItems(items: List<BookmarkModel>) {
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BookmarkItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onBookmarkChecked
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: BookmarkItemBinding,
        private val onBookmarkChecked: (Int, BookmarkModel) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BookmarkModel) = with(binding) {
            title.text = item.title
            description.text = item.description
            bookmark.isChecked = item.isBookmark

            // 북마크 클릭
            bookmark.setOnCheckedChangeListener { _, isChecked ->
                // 현재 바인딩된 아이템과 checked 된 값 비교 후 전달
                if (item.isBookmark != isChecked) {
                    onBookmarkChecked(
                        adapterPosition,
                        item.copy(
                            isBookmark = isChecked
                        )
                    )
                }
            }
        }
    }

}