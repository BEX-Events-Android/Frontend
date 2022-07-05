package com.example.db_events

import android.R
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.db_events.databinding.FragmentEventsListBinding


class EventsListFragment : Fragment(), EventAdapter.Callback {
    private lateinit var binding: FragmentEventsListBinding
    private lateinit var viewModel: EventsListViewModel

    lateinit var unmodifiedListOfEvents: ArrayList<EventModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentEventsListBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener {
            setFilter()
        }

        subscribeToVM()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.db_events.R.menu.profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            com.example.db_events.R.id.show_profile -> {
                view?.findNavController()?.navigate(com.example.db_events.R.id.action_eventsListFragment_to_profileFragment)
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        })
    }

    private fun subscribeToVM() {
        viewModel = ViewModelProvider(this)[EventsListViewModel::class.java]
        viewModel.result.observe(viewLifecycleOwner){ eventList ->
            if (eventList.isNotEmpty()) {
                unmodifiedListOfEvents = ArrayList(eventList)
                binding.eventsList.adapter = EventAdapter(unmodifiedListOfEvents, this)
            }
        }
    }

    private fun setFilter() {
        val modifiedListOfEvents = unmodifiedListOfEvents.filter { event -> event.location.equals("DB Main Room") } as ArrayList<EventModel>
        binding.eventsList.swapAdapter(EventAdapter(modifiedListOfEvents, this), false)
    }

    override fun onItemClicked(itemId: String) {
        val bundle = bundleOf("id" to itemId)
        view?.findNavController()
            ?.navigate(com.example.db_events.R.id.action_eventsListFragment_to_detailedEventFragment, bundle)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}
