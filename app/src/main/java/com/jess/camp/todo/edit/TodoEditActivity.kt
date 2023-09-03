package com.jess.camp.todo.edit

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.jess.camp.databinding.TodoEditActivityBinding
import com.jess.camp.todo.add.TodoAddActivity.Companion.EXTRA_MODEL
import com.jess.camp.todo.home.TodoFragment
import com.jess.camp.todo.home.TodoFragment.Companion.EXTRA_POSITION
import com.jess.camp.todo.home.TodoModel


class TodoEditActivity : AppCompatActivity() {
    private lateinit var binding: TodoEditActivityBinding

    companion object {
        fun newIntent(context: FragmentActivity?) = Intent(context, TodoEditActivity::class.java)
        const val EXTRA_MODEL_MODIFY = "extra_model_modify"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TodoEditActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        toolBar.setNavigationOnClickListener {
            finish()
        }

        val todoModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_MODEL, TodoModel::class.java)
        } else {
            intent.getParcelableExtra(
                EXTRA_MODEL
            )
        }
        val position = intent.getIntExtra(EXTRA_POSITION, 0)

        todoTitle.setText(todoModel!!.title)
        todoDescription.setText(todoModel!!.description)

        modifyBtn.setOnClickListener {
            val intent = Intent().apply {
                putExtra(
                    EXTRA_MODEL_MODIFY, TodoModel(
                        todoTitle.text.toString(),
                        todoDescription.text.toString()
                    )
                )
                putExtra(EXTRA_POSITION, position)
            }
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        deleteBtn.setOnClickListener {
            val builder = AlertDialog.Builder(this@TodoEditActivity)
            builder.setTitle("할 일을 삭제하실건가요?")

            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    when (p1) {
                        DialogInterface.BUTTON_POSITIVE ->
                            intent = Intent().apply {
                                putExtra(EXTRA_POSITION, position)
                                setResult(Activity.RESULT_CANCELED, intent)
                                finish()
                            }
                    }
                }
            }

            builder.setNegativeButton("취소", null)
            builder.setPositiveButton("삭제", listener)

            builder.show()
        }

    }
}