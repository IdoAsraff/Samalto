package com.talido.samalto.view

import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R

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

    fun bindAddPost(adapter: PostsAdapter) {
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
