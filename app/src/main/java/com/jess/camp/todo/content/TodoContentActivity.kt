package com.jess.camp.todo.content

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.jess.camp.databinding.TodoContentActivityBinding
import com.jess.camp.todo.home.TodoModel


class TodoContentActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MODEL = "extra_model"
        const val EXTRA_ENTRY_TYPE = "extra_entry_type"
        const val EXTRA_POSITION = "extra_position"

        fun newIntentForAdd(
            context: Context
        ) = Intent(context, TodoContentActivity::class.java).apply{
            putExtra(EXTRA_ENTRY_TYPE, TodoContentType.ADD.name)
        }

        fun newIntentForEdit(
            context: Context,
            position: Int,
            item: TodoModel
        ) = Intent(context, TodoContentActivity::class.java).apply{
            putExtra(EXTRA_ENTRY_TYPE, TodoContentType.EDIT.name)
            putExtra(EXTRA_POSITION, position)
            putExtra(EXTRA_MODEL, item )
        }
    }

    private lateinit var binding: TodoContentActivityBinding


    // newIntentForAdd에서 받아온 string값을 Enum클래스로 변경해줌.
    private val entryType : TodoContentType? by lazy {
        TodoContentType.from(intent.getStringExtra(EXTRA_ENTRY_TYPE))
    }

    // TODO : 인텐트로 받아온 스트링 값 그 자체로 구분해서 쓰면 되지 않을까? 굳이 from함수로 enum클래스로 바꿔서 쓰는 이유가 있을가?

//    fun test() {
//        when (entryType) {
//            TodoContentType.ADD -> TODO()
//            TodoContentType.EDIT -> TODO()
//            TodoContentType.DELETE -> TODO()
//            null -> TODO()
//        }
//    }

    private val item by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(
                EXTRA_MODEL,
                TodoModel::class.java
            )
        } else {
            intent?.getParcelableExtra(
                EXTRA_MODEL
            )
        }
    }

    // TODO: defaultValue를 -1로 설정한 이유가 뭔가요?!
    private val position by lazy {
        intent?.getIntExtra(EXTRA_POSITION, -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoContentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initData()
    }

    private fun initView() = with(binding) {

        // 뒤로가기
        toolBar.setNavigationOnClickListener {
            finish()
        }


        // 삭제 버튼
        delete.isVisible = entryType == TodoContentType.EDIT

//        when (entryType) {
//            TodoContentType.EDIT -> delete.visibility = View.VISIBLE
//            else -> delete.visibility = View.INVISIBLE
//        }

        delete.setOnClickListener {
            val intent = Intent().apply {
                putExtra(
                    EXTRA_ENTRY_TYPE, TodoContentType.DELETE.name
                )

                putExtra(
                    EXTRA_POSITION,
                    intent.getIntExtra(EXTRA_POSITION, -1)
                )
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }



        // 등록/수정 버튼
        submit.setText(
            when (entryType) {
                TodoContentType.ADD -> "등록"
                TodoContentType.EDIT -> "수정"
                else -> return
            }
        )

        // 등록 버튼
        submit.setOnClickListener {
            val intent = Intent().apply {
                putExtra(
                    EXTRA_ENTRY_TYPE, intent.getStringExtra(EXTRA_ENTRY_TYPE)
                )
                putExtra(
                    EXTRA_MODEL, TodoModel(
                        todoTitle.text.toString(),
                        todoDescription.text.toString()
                    )
                )
                putExtra(
                    EXTRA_POSITION,
                    intent.getIntExtra(EXTRA_POSITION, -1)
                    // 이걸 position으로 바꿔도 되는지 확인해보자
                )
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    // 수정시 제목과 내용에 값 채워넣기

    // TODO: setText를 null로 해도 상관없다고 하셨는데 그 이유가 궁금합니다!
    private fun initData() = with(binding) {
        if (entryType == TodoContentType.EDIT) {
            todoTitle.setText("${item?.title}")
            todoDescription.setText("${item?.description}")
        }
    }
}
