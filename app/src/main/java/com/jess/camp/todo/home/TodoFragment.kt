package com.jess.camp.todo.home

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.jess.camp.databinding.TodoFragmentBinding
import com.jess.camp.main.MainViewPagerAdapter
import com.jess.camp.todo.add.TodoAddActivity.Companion.EXTRA_MODEL
import com.jess.camp.todo.edit.TodoEditActivity

class TodoFragment : Fragment() {

    companion object {
        fun newInstance() = TodoFragment()
        const val EXTRA_POSITION = "position"

    }

    private val viewPagerAdapter by lazy {
        MainViewPagerAdapter(requireActivity())
    }

    private var _binding: TodoFragmentBinding? = null
    private val binding get() = _binding!!

    private val listAdapter by lazy {
        TodoListAdapter()
    }

    private val editTodoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(
                        TodoEditActivity.EXTRA_MODEL_MODIFY,
                        TodoModel::class.java
                    )
               } else {
                    result.data?.getParcelableExtra(
                        TodoEditActivity.EXTRA_MODEL_MODIFY
                    )
               }
                val position = result.data?.getIntExtra(EXTRA_POSITION, 0)


//                todoFragment?.setModifyTodoContent(todoModel, position)
                listAdapter.modifyItem(todoModel, position)
//                this.setModifyTodoContent(todoModel, position)

            }
            else {
                val position = result.data?.getIntExtra(EXTRA_POSITION, 0)
                listAdapter.deleteItem(position)
            }
        }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodoFragmentBinding.inflate(inflater, container, false)

        listAdapter.itemClick = object : TodoListAdapter.ItemClick{
            override fun onClick(view: View, position: Int, list: TodoModel) {
                    val intent = TodoEditActivity.newIntent(activity)
                    intent.putExtra(EXTRA_MODEL, TodoModel(list.title, list.description))
                    intent.putExtra(EXTRA_POSITION, position)
                    editTodoLauncher.launch(intent)
            }
        }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    private fun initView() = with(binding) {
        todoList.adapter = listAdapter
    }

    fun setTodoContent(todoModel: TodoModel?) {
        listAdapter.addItem(todoModel)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}