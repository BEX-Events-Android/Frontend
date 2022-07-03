package com.example.db_events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.db_events.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.loginButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
        binding.registerButton.setOnClickListener {
            register()
        }

        subscribeToVM()

        return binding.root
    }

    private fun register() {
        val email: String = binding.emailInput.text.toString()
        val password: String = binding.passwordInput.text.toString()
        val matchingPassword: String = binding.passwordConfirmInput.text.toString()

        if (!validateData(email, password, matchingPassword)) {
            return
        }

        viewModel.register(RegisterRequest(email, password, matchingPassword))
    }

    private fun subscribeToVM() {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]

        viewModel.registerSuccessful.observe(viewLifecycleOwner) { isSuccessful ->
            if (!isSuccessful) {
                Toast.makeText(activity, "Email is already in use!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = bundleOf("email" to binding.emailInput.text.toString(), "password" to binding.passwordInput.text.toString())
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment, bundle)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }
//
//    override fun onStop() {
//        super.onStop()
//        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
//    }

    private fun validateData(username: String, password: String, matchingPassword: String): Boolean {
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
        if (!password.equals(matchingPassword)) {
            Toast.makeText(activity, "Passwords should match!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
