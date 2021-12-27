package com.talido.samalto.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.talido.samalto.R
import com.talido.samalto.model.data.Guard
import com.talido.samalto.model.data.Post
import java.util.*

class ScheduleActivity : AppCompatActivity() {
    public var startTime: Optional<Calendar> = Optional.empty()
    public var posts: Optional<List<Post>> = Optional.empty()
    public var guards: Optional<List<Guard>> = Optional.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
    }
}