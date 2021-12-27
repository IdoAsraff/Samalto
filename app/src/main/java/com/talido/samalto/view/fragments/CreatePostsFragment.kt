package com.talido.samalto.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talido.samalto.databinding.FragmentCreatePostsBinding
import com.talido.samalto.view.ScheduleActivity

class CreatePostsFragment : Fragment() {
    private lateinit var binding: FragmentCreatePostsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreatePostsBinding.inflate(layoutInflater)

        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.next.setOnClickListener {
            findNavController().navigate(CreatePostsFragmentDirections.actionCreatePostsFragmentToGuardsAmountFragment())
        }

        return binding.root
    }
}