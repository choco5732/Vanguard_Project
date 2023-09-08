package com.jess.camp.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jess.camp.databinding.BookmarkFragmentBinding
import com.jess.camp.main.MainActivity

class BookmarkFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarkFragment()
    }

    private var _binding: BookmarkFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        BookmarkListAdapter { position, item ->
            modifyItemAtTodoTab(item)
            removeItem(position)
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
    }

    private fun initView() = with(binding) {
        bookmarkList.adapter = listAdapter
    }

    fun addItem(
        item: BookmarkModel
    ) {
        listAdapter.addItem(item)
    }

    private fun removeItem(
        position: Int
    ) {
        listAdapter.removeItem(position)
    }

    private fun modifyItemAtTodoTab(
        item: BookmarkModel
    ) {
        (activity as? MainActivity)?.modifyTodoItem(item)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}