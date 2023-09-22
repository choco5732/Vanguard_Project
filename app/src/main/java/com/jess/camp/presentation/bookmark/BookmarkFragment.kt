package com.jess.camp.presentation.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.jess.camp.databinding.BookmarkFragmentBinding
import com.jess.camp.presentation.main.MainSharedEventForBookmark
import com.jess.camp.presentation.main.MainSharedViewModel

class BookmarkFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarkFragment()
    }

    private var _binding: BookmarkFragmentBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: MainSharedViewModel by activityViewModels()
    private val viewModel: BookmarkViewModel by viewModels()

    private val listAdapter by lazy {
        BookmarkListAdapter { position, item ->
            viewModel.removeBookmarkItem(position)
            sharedViewModel.updateTodoItem(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BookmarkFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() = with(binding) {
        bookmarkList.adapter = listAdapter
    }

    private fun initViewModel() {
        with(viewModel) {
            list.observe(viewLifecycleOwner) {
                listAdapter.submitList(it)
            }
        }

        with(sharedViewModel) {
            bookmarkEvent.observe(viewLifecycleOwner) { event ->
                when (event) {
                    is MainSharedEventForBookmark.UpdateBookmarkItems -> {
                        viewModel.updateBookmarkItems(event.items)
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}