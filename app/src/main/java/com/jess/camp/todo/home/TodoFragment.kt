package com.jess.camp.todo.home

import android.app.Activity
import android.graphics.drawable.Drawable.ConstantState
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.jess.camp.databinding.TodoFragmentBinding
import com.jess.camp.todo.content.TodoContentActivity
import com.jess.camp.todo.content.TodoContentActivity.Companion.EXTRA_ENTRY_TYPE
import com.jess.camp.todo.content.TodoContentActivity.Companion.EXTRA_POSITION
import com.jess.camp.todo.content.TodoContentType

class TodoFragment : Fragment() {

    companion object {
        fun newInstance() = TodoFragment()
    }

    private var _binding: TodoFragmentBinding? = null
    private val binding get() = _binding!!

    private val editTodoLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                // 진입 타입
                val entryType =
                    result.data?.getStringExtra(EXTRA_ENTRY_TYPE)

                // 수정할 Recyclerview의 position
                val position =
                    result.data?.getIntExtra(EXTRA_POSITION, 0) ?: -1

                // 수정한 아이템
                val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    result.data?.getParcelableExtra(
                        TodoContentActivity.EXTRA_MODEL,
                        TodoModel::class.java
                    )
                } else {
                    result.data?.getParcelableExtra(
                        TodoContentActivity.EXTRA_MODEL
                    )
                }

                // resultcode로 무언가를 처리하면 안됨 무언의 약속!
                when (TodoContentType.from(entryType)) {
                    TodoContentType.EDIT -> modifyTodoItem(position, todoModel)
                    TodoContentType.DELETE -> deleteTodoItem(position)
                    else -> Unit
                }
            }
        }

    private fun modifyTodoItem(position: Int, item: TodoModel?) {
        listAdapter.editTodoItem(position, item)
    }
    private fun deleteTodoItem(position: Int) {
        listAdapter.deleteTodoItem(position)
    }



    // TODO: 질문!
    // TodoListAdapter(position, item)이 아닌 람다로 전달하는 문법을 여쭤보자!
    // 왜 fab버튼을 눌른 부분이 아닌 여기에 editTodoLauncher를 사용하는지 여쭤보자!

    // 이렇게 하면 아이템을 클릭함과 동시에 editTodoLauncher를 실행시켜서 수정할 수 있는 상태로 만들어준다.
    private val listAdapter by lazy {
        TodoListAdapter { position, item ->
            editTodoLauncher.launch(
                TodoContentActivity.newIntentForEdit(
                    requireContext(),
                    position,
                    item
                )
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TodoFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() = with(binding) {
        todoList.adapter = listAdapter
    }

    fun setDodoContent(todoModel: TodoModel?) {
        listAdapter.addItem(todoModel)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}