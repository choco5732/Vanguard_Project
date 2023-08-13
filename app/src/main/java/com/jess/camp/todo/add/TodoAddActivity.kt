package com.jess.camp.todo.add

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jess.camp.databinding.TodoAddActivityBinding


class TodoAddActivity : AppCompatActivity() {

    companion object {

        const val EXTRA_TODO_TITLE = "extra_todo_title"
        const val EXTRA_TODO_DESCRIPTION = "extra_todo_description"

        fun newIntent(context: Context) = Intent(context, TodoAddActivity::class.java)
    }

    private lateinit var binding: TodoAddActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoAddActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {

        toolBar.setNavigationOnClickListener {
            finish()
        }

        submit.setOnClickListener {
            val intent = Intent().apply {
                putExtra(EXTRA_TODO_TITLE, todoTitle.text.toString())
                putExtra(EXTRA_TODO_DESCRIPTION, todoDescription.text.toString())
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}