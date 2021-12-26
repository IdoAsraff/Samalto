package com.talido.samalto.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talido.samalto.R
import com.talido.samalto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createSchedule.setOnClickListener {
            val intent = Intent(this, ScheduleStartActivity::class.java)
            startActivity(intent)
        }
    }
}