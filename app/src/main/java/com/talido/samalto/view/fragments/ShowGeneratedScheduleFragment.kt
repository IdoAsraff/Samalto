package com.talido.samalto.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talido.samalto.databinding.FragmentShowGeneratedScheduleBinding

class ShowGeneratedScheduleFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentShowGeneratedScheduleBinding.inflate(layoutInflater)

        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}