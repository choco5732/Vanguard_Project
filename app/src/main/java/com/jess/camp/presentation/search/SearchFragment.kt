package com.jess.camp.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jess.camp.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private var _binding: SearchFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels {
        SearchViewModelFactory()
    }

    private val adapter: SearchListAdapter by lazy {
        SearchListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()

        // test
        viewModel.search("kotlin")
    }

    private fun initView() = with(binding) {
        searchList.adapter = adapter
    }

    private fun initViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}