package com.talido.samalto.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.talido.samalto.databinding.FragmentCreatePostsBinding
import com.talido.samalto.model.data.Post
import com.talido.samalto.view.PostsAdapter

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
        binding.postsList.adapter = PostsAdapter(binding.root.context, mutableListOf(Post("פטרול", 3), Post("כפתורים", 4)))
        (binding.postsList.adapter as PostsAdapter).notifyDataSetChanged()
        return binding.root
    }
}