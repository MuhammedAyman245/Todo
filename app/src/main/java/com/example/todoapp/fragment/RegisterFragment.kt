package com.example.todoapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.todoapp.R
import com.example.todoapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
    private lateinit var auth :FirebaseAuth
    private lateinit var navController: NavController
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
    }

    private fun init(view:View){
        navController = Navigation.findNavController(view)
        auth = FirebaseAuth.getInstance()
    }

    private fun registerEvents() {
        binding.logInTextView.setOnClickListener{
            navController.navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.next.setOnClickListener{
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            val verifyPass = binding.passwordValidation.text.toString()

            if(email.isNotEmpty() && password.isNotEmpty() && verifyPass.isNotEmpty()){
                binding.progressBar.visibility = View.VISIBLE
                if(password == verifyPass){
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, "Register Succefully", Toast.LENGTH_SHORT)
                                .show()
                            navController.navigate(R.id.action_registerFragment_to_homeFragment)
                        } else {
                            Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                        binding.progressBar.visibility = View.GONE
                    }
                }
            } else {
                Toast.makeText(context, "Empty fields are not allow", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}