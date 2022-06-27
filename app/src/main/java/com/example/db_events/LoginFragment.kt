package com.example.db_events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.db_events.databinding.FragmentLoginBinding
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.registerButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.loginButton.setOnClickListener{ view: View ->
            login()
        }

        subscribeToVM()

        return binding.root
    }

    private fun login() {
        val email: String = binding.emailInput.text.toString()
        val password: String = binding.passwordInput.text.toString()

        if (!validateData(email, password)) {
            return
        }

        viewModel.login(LoginRequest(email, password))
    }

    private fun subscribeToVM() {
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.error.observe(viewLifecycleOwner) { err ->
            Toast.makeText(activity, "Something went wrong!", Toast.LENGTH_SHORT).show()
        }

        viewModel.result.observe(viewLifecycleOwner){ userModel ->
            findNavController().navigate(R.id.action_loginFragment_to_eventsList)
        }
    }

    private fun validateData(username: String, password: String): Boolean {
        if (username.trim().isEmpty()) {
            Toast.makeText(activity, "Username can't be empty!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.trim().isEmpty()) {
            Toast.makeText(activity, "Password can't be empty!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.trim().length < 4) {
            Toast.makeText(activity, "Password should be at least 4 characters long!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}