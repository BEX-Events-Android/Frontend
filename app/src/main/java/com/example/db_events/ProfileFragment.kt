package com.example.db_events

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.db_events.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), EventAdapter.Callback {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.signOut.setOnClickListener{view: View ->
                signout()
            view.findNavController().navigate(com.example.db_events.R.id.action_profileFragment_to_loginFragment)
            }

        subscribeToVM()

        return binding.root
    }

    private fun subscribeToVM() {
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        viewModel.result.observe(viewLifecycleOwner){ profile ->
            if (profile.pastEvents.isNotEmpty()) {
                binding.previousEventsList.adapter = EventAdapter(profile.pastEvents, this)
            }
            if (profile.upcomingEvents.isNotEmpty()) {
                binding.futureEventsList.adapter = EventAdapter(profile.upcomingEvents, this)
            }

            binding.fullName.setText(profile.lastName + " " + profile.firstName)

            binding.email.setText(profile.email)
        }

        viewModel.navigationEvent.observe(viewLifecycleOwner){status ->
            if(status == true) {
                view?.findNavController()?.navigate(com.example.db_events.R.id.action_profileFragment_to_loginFragment)
            }
        }
    }

    override fun onItemClicked(itemId: String) {
        val bundle = bundleOf("id" to itemId)
        view?.findNavController()
            ?.navigate(com.example.db_events.R.id.action_eventsListFragment_to_detailedEventFragment, bundle)
    }

    private fun signout() {
        viewModel.signOut()
    }
}