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
            (activity as ScheduleActivity).posts = Optional.of((binding.postsList.adapter as PostsAdapter).adapterPosts.toList())
            findNavController().navigate(CreatePostsFragmentDirections.actionCreatePostsFragmentToGuardsAmountFragment())
        }

        binding.postsList.layoutManager = LinearLayoutManager(this.requireContext())
        binding.postsList.itemAnimator = DefaultItemAnimator()
        // Sample data
        val testList = mutableListOf<Post>()
        (activity as ScheduleActivity).posts.ifPresent {
            testList.addAll((activity as ScheduleActivity).posts.get())
        }
            /*testList.addAll(mutableListOf(Post("פטרול", 3), Post("כפתורים", 4)))
            val startTime = Calendar.getInstance()
            val endTime = Calendar.getInstance()
            startTime[Calendar.HOUR_OF_DAY] = 15
            startTime[Calendar.MINUTE] = 15
            endTime[Calendar.HOUR_OF_DAY] = 17
            endTime[Calendar.MINUTE] = 15
            testList[0].addShift(startTime, endTime)
            val otherEndTime = Calendar.getInstance()
            otherEndTime [Calendar.HOUR_OF_DAY] = 19
            otherEndTime[Calendar.MINUTE] = 15
            testList[0].addShift(endTime, otherEndTime)*/

        // Sample data
        binding.postsList.adapter = PostsAdapter(binding.root.context, (activity as ScheduleActivity).startTime.get(), testList)
        return binding.root
    }
}