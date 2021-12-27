package com.talido.samalto.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.talido.samalto.databinding.ActivityShowGeneratedScheduleBinding

class ShowGeneratedScheduleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityShowGeneratedScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}