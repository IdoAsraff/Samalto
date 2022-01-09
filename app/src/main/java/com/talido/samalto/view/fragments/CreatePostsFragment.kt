package com.talido.samalto.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.talido.samalto.databinding.FragmentCreatePostsBinding
import com.talido.samalto.view.ScheduleActivity
import com.talido.samalto.model.data.Post
import com.talido.samalto.view.PostsAdapter
import java.util.*

class CreatePostsFragment : Fragment() {
    private lateinit var binding: FragmentCreatePostsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCreatePostsBinding.inflate(layoutInflater)

        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.next.setOnClickListener {
            findNavController().navigate(CreatePostsFragmentDirections.actionCreatePostsFragmentToGuardsAmountFragment())
        }

        binding.postsList.layoutManager = LinearLayoutManager(this.requireContext())
        binding.postsList.itemAnimator = DefaultItemAnimator()
        val testList = mutableListOf(Post("פטרול", 3), Post("כפתורים", 4))
        val startTime = Calendar.getInstance()
        val endTime = Calendar.getInstance()
        startTime[Calendar.HOUR_OF_DAY] = 10
        startTime[Calendar.MINUTE] = 10
        endTime[Calendar.HOUR_OF_DAY] = 11
        endTime[Calendar.MINUTE] = 11
        testList[0].addShift(startTime, endTime)
        binding.postsList.adapter = PostsAdapter(binding.root.context, testList)
        return binding.root
    }
}