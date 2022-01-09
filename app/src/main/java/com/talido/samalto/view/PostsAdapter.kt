package com.talido.samalto.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Post

class PostsAdapter(val context: Context, var posts: MutableList<Post>) :
    RecyclerView.Adapter<PostHolder>() {
    init {
        posts.add(Post("", 0))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(postHolder: PostHolder, position: Int) {
        // If this is the last item, do stuff
        if (position == posts.size - 1) {
            postHolder.bindEmpty(this)
        } else {
            postHolder.bindPost(posts[position], this)
        }

        postHolder.postName.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                posts[position].name = postHolder.postName.text.toString()
            }
        }

        postHolder.sufferingLevel.hint = position.toString()
        postHolder.sufferingLevel.setOnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val inputSuffering =  postHolder.sufferingLevel.text.toString()
                posts[position].sufferingLevel = if (inputSuffering != "") inputSuffering.toInt() else 0
            }
        }
    }

    fun updatePosts() {

    }

    override fun getItemCount(): Int {
        return posts.size
    }
}

class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val postCard: CardView = itemView.findViewById(R.id.postCard)
    val sufferingLevel: EditText = itemView.findViewById(R.id.sufferingLevel)
    val postName: EditText = itemView.findViewById(R.id.postName)
    val shiftList: RecyclerView = itemView.findViewById(R.id.shiftsList)
    val addShift: ImageView = itemView.findViewById(R.id.addPost)
    var isExpanded = false

    fun bindPost(post: Post, adapter: PostsAdapter) {
        postName.setText(post.name)
        sufferingLevel.setText(post.sufferingLevel.toString())
        shiftList.layoutManager = LinearLayoutManager(postCard.context)
        shiftList.adapter = ShiftAdapter(postCard.context, post.shifts)
        shiftList.visibility = if (isExpanded) View.GONE else View.VISIBLE
        addShift.visibility = View.GONE

        postCard.setOnClickListener {
            isExpanded = !isExpanded
            adapter.notifyDataSetChanged()
        }
    }

    fun bindEmpty(adapter: PostsAdapter) {
        postName.setText("")
        sufferingLevel.setText("0")
        postName.hint = "שם עמדה"
        addShift.setOnClickListener {
            adapter.posts.add(Post())
            adapter.notifyDataSetChanged()
        }
    }
}
