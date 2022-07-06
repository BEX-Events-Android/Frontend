package com.example.db_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.db_events.databinding.FragmentCustomDialogBinding
import com.example.db_events.databinding.FragmentDatePickDialogBinding
import java.text.SimpleDateFormat
import java.util.*

class DatePickDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDatePickDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatePickDialogBinding.inflate(inflater, container, false)

        val today = Calendar.getInstance()
        binding.datePicker.init(today.get(Calendar.YEAR), today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)) {
                view, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            val format = SimpleDateFormat("dd-MM-yy")
            val msg = format.format(calendar.time)
            binding.submitButton.setOnClickListener {
                requireActivity().supportFragmentManager.setFragmentResultListener("date", viewLifecycleOwner) {
                    requestKey, result ->
                    val fragment = result.get("datepick") as CustomDialogFragment
                    fragment.setPickedDate(msg)
                    dismiss()
                }
            }
        }

        binding.cancelButton.setOnClickListener {
            dismiss()
        }



        return binding.root
    }

}