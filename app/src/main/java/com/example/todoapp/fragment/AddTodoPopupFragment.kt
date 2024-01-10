package com.example.todoapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todoapp.databinding.FragmentAddTodoPopupBinding
import com.example.todoapp.utils.TodoData
import com.google.android.material.textfield.TextInputEditText

class AddTodoPopupFragment : DialogFragment() {
    private lateinit var binding: FragmentAddTodoPopupBinding
    private lateinit var listner: DialogNextBtnClickListener
    private var todoData:TodoData? = null

    fun setListener(listener: DialogNextBtnClickListener){
        this.listner=listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddTodoPopupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(arguments != null){
            todoData = TodoData(arguments?.getString("taskId").toString(),arguments?.getString("task").toString())
            binding.todoEt.setText(todoData?.task)
        }
        addNewTodo()
    }

    companion object{
        const val TAG = "AddTodoPopupFragment"
        @JvmStatic
        fun newInstance(taskId:String, task:String) = AddTodoPopupFragment().apply {
            arguments = Bundle().apply {
                putString("taskId",taskId)
                putString("task",task)
            }
        }
    }


    private fun addNewTodo() {
        binding.todoNextBtn.setOnClickListener{
            val todoTask = binding.todoEt.text.toString()
            if(todoTask.isNotEmpty()) {
                if(todoData == null){
                    listner.onSaveTask(todoTask,binding.todoEt)
                }else{
                    todoData?.task = todoTask
                    listner.onUpdateTask(todoData!!, binding.todoEt)
                }
            }else{
                Toast.makeText(context, "Please Type any thing", Toast.LENGTH_SHORT).show()
            }
        }
        binding.todoClose.setOnClickListener{
            dismiss()
        }
    }

    interface DialogNextBtnClickListener{
        fun onSaveTask(todo:String,todoET: TextInputEditText)
        fun onUpdateTask(todoData:TodoData, todoEt: TextInputEditText)
    }

}