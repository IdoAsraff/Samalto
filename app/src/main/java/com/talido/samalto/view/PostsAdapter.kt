package com.talido.samalto.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Post
import com.talido.samalto.view.fragments.CreatePostsFragment

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
        // If this is the last item, do stuff
        if (position == adapterPosts.size - 1) {
            postHolder.bindEmpty(this)
        } else {
            postHolder.bindPost(this, position)
        }

        postHolder.postName.doAfterTextChanged {
            adapterPosts[position].name = postHolder.postName.text.toString()
        }

        postHolder.sufferingLevel.hint = position.toString()
        postHolder.sufferingLevel.doAfterTextChanged {
            val inputSuffering =  postHolder.sufferingLevel.text.toString()
            adapterPosts[position].sufferingLevel = if (inputSuffering != "") inputSuffering.toInt() else 0
        }
    }

    override fun getItemCount(): Int {
        return adapterPosts.size
    }
}
class AdapterPost(var isExpanded: Boolean = true): Post() {
    constructor(post: Post) : this() {
        name = post.name
        sufferingLevel = post.sufferingLevel
        shifts = post.shifts
    }
}

class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val postCard: CardView = itemView.findViewById(R.id.postCard)
    val sufferingLevel: EditText = itemView.findViewById(R.id.sufferingLevel)
    val postName: EditText = itemView.findViewById(R.id.postName)
    val shiftList: RecyclerView = itemView.findViewById(R.id.shiftsList)
    val addShift: ImageView = itemView.findViewById(R.id.addPost)
    val expand: ImageView = itemView.findViewById(R.id.expand)

    fun bindPost(adapter: PostsAdapter, position: Int) {
        val post = adapter.adapterPosts[position]

        postName.setText(post.name)
        sufferingLevel.setText(post.sufferingLevel.toString())
        shiftList.layoutManager = LinearLayoutManager(postCard.context)
        shiftList.adapter = ShiftAdapter(postCard.context, post.shifts)
        shiftList.visibility = if (post.isExpanded) View.VISIBLE else View.GONE
        addShift.visibility = View.GONE
        expand.visibility = View.VISIBLE
        if (post.isExpanded)
            expand.setImageDrawable(adapter.context.getDrawable(R.drawable.ic_expand_less_black_24dp))
        else
            expand.setImageDrawable(adapter.context.getDrawable(R.drawable.ic_expand_more_black_24dp))


        postCard.setOnClickListener {
            (adapter.context as AppCompatActivity).runOnUiThread {
                post.isExpanded = !post.isExpanded
            }
            adapter.notifyItemChanged(position)
        }
    }

    fun bindEmpty(adapter: PostsAdapter) {
        postName.setText("")
        sufferingLevel.setText("0")
        postName.hint = "שם עמדה"
        addShift.visibility = View.VISIBLE
        expand.visibility = View.GONE
        addShift.setOnClickListener {
            adapter.adapterPosts.add(AdapterPost())
            adapter.notifyItemChanged(adapter.adapterPosts.size - 2) // Remove + from last item
            adapter.notifyItemInserted(adapter.adapterPosts.size - 1) // Add new item
        }
    }
}
