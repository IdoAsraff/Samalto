package com.talido.samalto.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talido.samalto.databinding.FragmentShowGeneratedScheduleBinding

class ShowGeneratedScheduleFragment : Fragment() {
    private lateinit var binding: FragmentShowGeneratedScheduleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowGeneratedScheduleBinding.inflate(layoutInflater)

        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}