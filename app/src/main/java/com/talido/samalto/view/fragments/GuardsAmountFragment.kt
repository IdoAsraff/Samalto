package com.talido.samalto.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talido.samalto.databinding.FragmentGuardsAmountBinding

class GuardsAmountFragment : Fragment() {
    private lateinit var binding: FragmentGuardsAmountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuardsAmountBinding.inflate(inflater, container, false)

        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.next.setOnClickListener {
            findNavController().navigate(GuardsAmountFragmentDirections.actionGuardsAmountFragmentToShowGeneratedScheduleFragment())
        }

        return binding.root
    }
}