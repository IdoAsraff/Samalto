package com.talido.samalto.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talido.samalto.databinding.ActivityCreatePostsBinding
import com.talido.samalto.databinding.ActivityGuardsAmountBinding

class GuardsAmountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGuardsAmountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuardsAmountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.prev.setOnClickListener {
            finish()
        }
        binding.next.setOnClickListener {
            val intent = Intent(this, ShowGeneratedScheduleActivity::class.java).apply {
            }
            startActivity(intent)
        }
    }
}