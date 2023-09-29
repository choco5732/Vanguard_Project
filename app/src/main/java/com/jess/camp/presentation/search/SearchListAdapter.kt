package com.jess.camp.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.jess.camp.databinding.SearchImageItemBinding
import com.jess.camp.databinding.SearchUnknownItemBinding
import com.jess.camp.databinding.SearchVideoItemBinding

class SearchListAdapter(
    private val onClick: (SearchItem) -> Unit
) : ListAdapter<SearchItem, SearchListAdapter.ViewHolder>(

    object : DiffUtil.ItemCallback<SearchItem>() {
        override fun areItemsTheSame(
            oldItem: SearchItem,
            newItem: SearchItem
        ): Boolean = if (oldItem is SearchItem.ImageItem && newItem is SearchItem.ImageItem) {
            oldItem.title == newItem.title
        } else if (oldItem is SearchItem.VideoItem && newItem is SearchItem.VideoItem) {
            oldItem.title == newItem.title
        } else {
            oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SearchItem,
            newItem: SearchItem
        ): Boolean = oldItem == newItem
    }
) {

    enum class SearchItemViewType {
        IMAGE, VIDEO
    }

    abstract class ViewHolder(
        root: View
    ) : RecyclerView.ViewHolder(root) {

        abstract fun onBind(item: SearchItem)
    }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is SearchItem.ImageItem -> SearchItemViewType.IMAGE.ordinal
        is SearchItem.VideoItem -> SearchItemViewType.VIDEO.ordinal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        when (viewType) {
            SearchItemViewType.IMAGE.ordinal ->
                ImageViewHolder(
                    SearchImageItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick
                )

            SearchItemViewType.VIDEO.ordinal ->
                VideoViewHolder(
                    SearchVideoItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ),
                    onClick
                )

            else -> UnknownViewHolder(
                SearchUnknownItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class ImageViewHolder(
        private val binding: SearchImageItemBinding,
        private val onClick: (SearchItem) -> Unit
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = with(binding) {
            if (item is SearchItem.ImageItem) {
                title.text = item.title
                thumbnail.load(item.thumbnail)

                container.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    class VideoViewHolder(
        private val binding: SearchVideoItemBinding,
        private val onClick: (SearchItem) -> Unit
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = with(binding) {
            if (item is SearchItem.VideoItem) {
                title.text = item.title
                thumbnail.load(item.thumbnail)

                container.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    class UnknownViewHolder(
        binding: SearchUnknownItemBinding
    ) : ViewHolder(binding.root) {

        override fun onBind(item: SearchItem) = Unit
    }
}
