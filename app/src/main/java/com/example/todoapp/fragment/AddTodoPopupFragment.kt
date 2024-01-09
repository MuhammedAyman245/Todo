package com.example.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentAddTodoPopupBinding
import com.example.todoapp.databinding.FragmentHomeBinding
import com.google.android.material.textfield.TextInputEditText

class AddTodoPopupFragment : DialogFragment() {
    private lateinit var binding: FragmentAddTodoPopupBinding
    private lateinit var listner: DialogNextBtnClickListener

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
        addNewTodo()
    }

    private fun addNewTodo() {
        binding.todoNextBtn.setOnClickListener{
            val todoTask = binding.todoEt.text.toString()
            if(todoTask.isNotEmpty()) {
                listner.onSaveTask(todoTask,binding.todoEt)
            }else{
                Toast.makeText(context, "Please Type any thing", Toast.LENGTH_SHORT).show()
            }
        }
        binding.todoClose.setOnClickListener{
            dismiss()
        }
    }

    interface DialogNextBtnClickListener{
        fun onSaveTask(todo:String,TodoET: TextInputEditText)
    }

}