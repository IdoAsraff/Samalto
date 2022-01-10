package com.talido.samalto.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Post
import com.talido.samalto.model.data.Shift
import java.util.*

class AdapterPost : Post {
    var isExpanded: Boolean = false

    constructor(post: Post) : this() {
        name = post.name
        sufferingLevel = post.sufferingLevel
        shifts = post.shifts
    }
    constructor(name: String = "", sufferingLevel: Int=0, shifts: MutableList<Shift> = mutableListOf()) : super(name, sufferingLevel, shifts)
}

class PostsAdapter(val context: Context, val startTime: Calendar, var posts: MutableList<Post>) :
    RecyclerView.Adapter<PostHolder>() {
    var adapterPosts: MutableList<AdapterPost> = (posts.map { AdapterPost(it) } as MutableList<AdapterPost>)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return PostHolder(view, this)
    }

    override fun onBindViewHolder(postHolder: PostHolder, position: Int) {
        if (position == adapterPosts.size) {
            postHolder.bindAddPost(this)
        } else {
            postHolder.bindPost(this, position)
        }
    }

    override fun getItemCount(): Int {
        return adapterPosts.size + 1
    }
}
