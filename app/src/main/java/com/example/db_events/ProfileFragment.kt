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
        viewModel.result.observe(viewLifecycleOwner){ eventList ->
            if (eventList.isNotEmpty()) {
                binding.futureEventsList.adapter = EventAdapter(eventList, this)
                binding.previousEventsList.adapter = EventAdapter(eventList, this)
            }
        }

        viewModel.navigationEvent.observe(viewLifecycleOwner){status ->
            if(status == true) {
                view?.findNavController()?.navigate(com.example.db_events.R.id.action_profileFragment_to_loginFragment)
            }
        }
    }

    override fun onItemClicked(itemId: String) {
        val bundle = bundleOf("id" to itemId)
//        view?.findNavController()
//            ?.navigate(R.id.action_eventsListFragment_to_detailedEventFragment, bundle)
    }

    private fun signout() {
        viewModel.signOut()
    }
}