package com.talido.samalto.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talido.samalto.R
import com.talido.samalto.databinding.FragmentScheduleStartBinding
import com.talido.samalto.model.data.toLocalTimeString
import com.talido.samalto.view.ScheduleActivity
import java.util.*

class ScheduleStartFragment : Fragment() {
    lateinit var binding: FragmentScheduleStartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentScheduleStartBinding.inflate(layoutInflater)

        if ((activity as ScheduleActivity).startTime.isPresent) {
            binding.schedStartInput.setText((activity as ScheduleActivity).startTime.get().toLocalTimeString())
            enableNextButton()
        }

        binding.schedStartInput.setOnDateTimeSetListener { view, datetime ->
            (activity as ScheduleActivity).startTime = Optional.of(datetime)
            binding.schedStartInput.setText(datetime.toLocalTimeString())
            enableNextButton()
        }

        binding.prev.setOnClickListener {
            requireActivity().finish()
        }
        binding.next.setOnClickListener {
            findNavController().navigate(ScheduleStartFragmentDirections.actionScheduleStartFragmentToCreatePostsFragment())
        }

        return binding.root
    }

    private fun enableNextButton() {
        binding.next.isEnabled = true
        binding.next.setTextColor(resources.getColor(R.color.textColorPrimary))
    }
}