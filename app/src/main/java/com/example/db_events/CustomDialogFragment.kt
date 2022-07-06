package com.example.db_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.db_events.databinding.FragmentCustomDialogBinding
import java.io.Serializable


class CustomDialogFragment : DialogFragment(), Serializable {

    private lateinit var binding: FragmentCustomDialogBinding

    private lateinit var locationsList: ArrayList<String>

    fun newInstance(locationsList: ArrayList<String>): CustomDialogFragment? {
        val f = CustomDialogFragment()

        val args = bundleOf("locations" to locationsList)
        f.setArguments(args)
        return f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationsList = arguments?.getStringArrayList("locations")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(inflater, container, false)

        for(i in locationsList.indices) {
            val rb = RadioButton(getActivity())

            rb.text = locationsList.get(i)
            rb.id = i

            binding.locationRadioGroup.addView(rb)
        }

        val rb = RadioButton(getActivity())

        rb.text = "None"
        rb.id = locationsList.size

        binding.locationRadioGroup.addView(rb)

        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        binding.submitButton.setOnClickListener {

            val selectedId = binding.locationRadioGroup.checkedRadioButtonId
            val radio = binding.locationRadioGroup.findViewById<RadioButton>(selectedId)

            val location = radio.text.toString()

            requireActivity().supportFragmentManager.setFragmentResultListener(
                "location",
                viewLifecycleOwner
            ) { requestKey, result ->
                val fragment = result.get("location") as EventsListFragment
                fragment.setFilter(location, binding.pickedDate.text.toString())
            }
            dismiss()
        }

        binding.datePickerButton.setOnClickListener {
            var dialog = DatePickDialogFragment()
            requireActivity().supportFragmentManager.setFragmentResult("date", bundleOf("datepick" to this@CustomDialogFragment))
            dialog!!.show(requireActivity().supportFragmentManager, "testDialog")
        }

        return binding.root
    }

    fun setPickedDate (date : String) {
        binding.pickedDate.setText(date)
    }

}
