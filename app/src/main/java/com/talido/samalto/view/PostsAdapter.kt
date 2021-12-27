package com.talido.samalto.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R
import com.talido.samalto.model.data.Post

class PostsAdapter(val context: Context, private var posts: MutableList<Post>) :
    RecyclerView.Adapter<PostHolder>() {
    init {
        posts.add(Post("", 0))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_row, parent, false)
        return PostHolder(view)
    }

    override fun onBindViewHolder(postHolder: PostHolder, position: Int) {
        with(postHolder) {
            // If this is the last item, do stuff
            if (position == posts.size - 1) {
                postName.setText("")
                sufferingLevel.setText("")
                postName.hint = "הכנס שם עמדה..."
            } else {
                postName.setText(posts[position].name)
                sufferingLevel.setText(posts[position].sufferingLevel.toString())
                shiftList.adapter = ShiftAdapter(context, posts[position].shifts)

                postCard.setOnClickListener {
                    val isVisible = shiftList.visibility == View.VISIBLE
                    shiftList.visibility = if (isVisible) View.GONE else View.VISIBLE
                    this@PostsAdapter.notifyDataSetChanged()
                }
            }

        }
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
}
