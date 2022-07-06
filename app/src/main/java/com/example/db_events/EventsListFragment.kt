package com.example.db_events

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.db_events.databinding.FragmentEventsListBinding
import java.io.Serializable


class EventsListFragment() : Fragment(), EventAdapter.Callback, Serializable {
    private lateinit var binding: FragmentEventsListBinding
    private lateinit var viewModel: EventsListViewModel

    lateinit var unmodifiedListOfEvents: ArrayList<EventModel>
    private lateinit var locationsList: ArrayList<String>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = FragmentEventsListBinding.inflate(inflater, container, false)

        binding.fab.setOnClickListener {
            var dialog = CustomDialogFragment().newInstance(locationsList)
            requireActivity().supportFragmentManager.setFragmentResult("location", bundleOf("location" to this@EventsListFragment))
            requireActivity().let { it1 -> dialog!!.show(it1.supportFragmentManager, "customDialog") }
        }

        subscribeToVM()

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.profile_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return (when(item.itemId) {
            R.id.show_profile -> {
                view?.findNavController()?.navigate(R.id.action_eventsListFragment_to_profileFragment)
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
        viewModel.locationFilter.observe(viewLifecycleOwner){ locationSet ->
            if (locationSet.isNotEmpty()) {
                locationsList = ArrayList(locationSet)
            }
        }
    }

    fun setFilter(location : String, date: String) {
        var modifiedListOfEvents = unmodifiedListOfEvents
        if(location != "None") {
            modifiedListOfEvents = modifiedListOfEvents.filter { event -> event.location.equals(location) } as ArrayList<EventModel>
        }
        if(date != "") {
            modifiedListOfEvents = modifiedListOfEvents.filter { event -> event.startDateTime.contains(date) } as ArrayList<EventModel>
        }

        binding.eventsList.swapAdapter(EventAdapter(modifiedListOfEvents, this), false)
    }

    override fun onItemClicked(itemId: String) {
        val bundle = bundleOf("id" to itemId)
        view?.findNavController()
            ?.navigate(R.id.action_eventsListFragment_to_detailedEventFragment, bundle)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}
