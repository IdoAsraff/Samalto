package com.talido.samalto.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Post

class AdapterPost(var isExpanded: Boolean = true): Post() {
    constructor(post: Post) : this() {
        name = post.name
        sufferingLevel = post.sufferingLevel
        shifts = post.shifts
    }
}

class PostsAdapter(val context: Context, var posts: MutableList<Post>) :
    RecyclerView.Adapter<PostHolder>() {
    var adapterPosts: MutableList<AdapterPost> = (posts.map { AdapterPost(it) } as MutableList<AdapterPost>)

    init {
        adapterPosts.add(AdapterPost())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(postHolder: PostHolder, position: Int) {
        if (position == adapterPosts.size - 1) {
            postHolder.bindAddPost(this)
        } else {
            postHolder.bindPost(this, position)
        }

        postHolder.postName.doAfterTextChanged {
            adapterPosts[position].name = postHolder.postName.text.toString()
        }
        postHolder.sufferingLevel.doAfterTextChanged {
            val inputSuffering =  postHolder.sufferingLevel.text.toString()
            adapterPosts[position].sufferingLevel = if (inputSuffering != "") inputSuffering.toInt() else 0
        }
    }

    override fun getItemCount(): Int {
        return adapterPosts.size
    }
}
