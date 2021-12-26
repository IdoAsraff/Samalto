package com.talido.samalto.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talido.samalto.R
import com.talido.samalto.databinding.ActivityScheduleStartBinding

class ScheduleStartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityScheduleStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.schedStartInput.setIs24HourView(true)

        binding.next.setOnClickListener {
            val startTime = binding.schedStartInput
            val intent = Intent(this, CreatePostsActivity::class.java).apply {
                putExtra("schedStartTime", 0)
            }
            startActivity(intent)
        }
    }

}