package com.example.db_events

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.db_events.databinding.FragmentDetailedEventBinding
import kotlin.random.Random

class DetailedEventFragment : Fragment() {
    private lateinit var binding: FragmentDetailedEventBinding
    private lateinit var id: String

    private lateinit var viewModelFactory: DetailedEventViewModelFactory
    private lateinit var viewModel: DetailedEventViewModel

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?,
        savedInstanceState: android.os.Bundle?
    ): View {
        binding = FragmentDetailedEventBinding.inflate(inflater, container, false)

        id = arguments?.getString("id")!!

        viewModelFactory = DetailedEventViewModelFactory(id)
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailedEventViewModel::class.java]

        subscribeToVM()

        return binding.root
    }

    private fun subscribeToVM() {
        viewModel.result.observe(viewLifecycleOwner) { event ->
            with(binding) {
                eventTitle.text = event.title.replaceFirstChar { c -> c.uppercase() }
                eventDescription.text = "Description\n${event.description}"
                eventDate.text = "Date: ${event.startDateTime.split(" ")[0]}"
                eventLocation.text = event.location
                eventOrganiser.text =
                    "Organiser: ${event.organiser.firstName} ${event.organiser.lastName}"

                // for now its random
                eventImage.setImageResource(photosList[Random.nextInt(0, photosList.size)])
            }
        }
    }
}