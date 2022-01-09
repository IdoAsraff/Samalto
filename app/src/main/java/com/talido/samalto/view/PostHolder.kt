package com.talido.samalto.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.talido.samalto.R

class PostHolder(itemView: View, adapter: PostsAdapter) : RecyclerView.ViewHolder(itemView) {
    val postCard: CardView = itemView.findViewById(R.id.postCard)
    val etSufferingLevel: EditText = itemView.findViewById(R.id.sufferingLevel)
    val etName: EditText = itemView.findViewById(R.id.postName)
    val shiftList: RecyclerView = itemView.findViewById(R.id.shiftsList)
    val addShift: ImageView = itemView.findViewById(R.id.addPost)
    val expand: ImageView = itemView.findViewById(R.id.expand)

    // Listeners
    val updatePostName = object : PositionableTextWatcher() {
        override fun afterTextChanged(inputName: Editable?) {
            if (position != null) {
                adapter.adapterPosts[position!!].name = inputName.toString()
            }
        }
    }
    val updatePostSuffering = object : PositionableTextWatcher() {
        override fun afterTextChanged(text: Editable?) {
            val inputSuffering = etSufferingLevel.text.toString()
            if (position != null) {
                adapter.adapterPosts[position!!].sufferingLevel =
                    if (inputSuffering != "") inputSuffering.toInt() else 0
            }
        }
    }

    init {
        etName.addTextChangedListener(updatePostName)
        etSufferingLevel.addTextChangedListener(updatePostSuffering)
    }

    fun bindPost(adapter: PostsAdapter, position: Int) {
        val post = adapter.adapterPosts[position]

        // Set fields
        etName.setText(post.name)
        etSufferingLevel.setText(post.sufferingLevel.toString())

        // Set field listeners
        updatePostName.position = position
        updatePostSuffering.position = position

        // Set Shift list
        shiftList.layoutManager = LinearLayoutManager(postCard.context)
        shiftList.adapter = ShiftAdapter(postCard.context, adapter.startTime, post.name, post.shifts)
        shiftList.visibility = if (post.isExpanded) View.VISIBLE else View.GONE
        addShift.visibility = View.GONE

        // Expand
        expand.visibility = View.VISIBLE
        if (post.isExpanded)
            expand.setImageDrawable(adapter.context.getDrawable(R.drawable.ic_expand_less_black_24dp))
        else
            expand.setImageDrawable(adapter.context.getDrawable(R.drawable.ic_expand_more_black_24dp))
        postCard.setOnClickListener {
            post.isExpanded = !post.isExpanded
            adapter.notifyItemChanged(position)
        }
    }

    fun bindAddPost(adapter: PostsAdapter) {
        etName.setText("")
        etSufferingLevel.setText("0")
        etName.hint = "שם עמדה"
        addShift.visibility = View.VISIBLE
        expand.visibility = View.GONE
        addShift.setOnClickListener {
            val inputSuffering = etSufferingLevel.text.toString()
            val newPost = AdapterPost(
                etName.text.toString(),
                if (inputSuffering != "") inputSuffering.toInt() else 0
            )
            adapter.adapterPosts.add(AdapterPost(newPost))
            adapter.notifyItemChanged(adapter.adapterPosts.size - 1) // Remove + from last item
            adapter.notifyItemInserted(adapter.adapterPosts.size) // Add new empty item
        }
    }

    abstract class PositionableTextWatcher : TextWatcher {
        var position: Int? = null
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }
}
