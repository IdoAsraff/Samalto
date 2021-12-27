package com.talido.samalto.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.talido.samalto.databinding.FragmentShowGeneratedScheduleBinding
import com.talido.samalto.model.Scheduler
import com.talido.samalto.model.data.assigned.Schedule
import com.talido.samalto.model.data.parsers.DescriptionTextCreator
import com.talido.samalto.view.ScheduleActivity

class ShowGeneratedScheduleFragment : Fragment() {
    private lateinit var binding: FragmentShowGeneratedScheduleBinding
    private lateinit var schedule: Schedule

    private val scheduler = Scheduler()
    private var descriptionTextCreator = DescriptionTextCreator()
    private var descriptionTextCreationFunc = descriptionTextCreator::createByGuard

    @ExperimentalStdlibApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowGeneratedScheduleBinding.inflate(layoutInflater)

        generateAndShowNewSchedule()

        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

    @ExperimentalStdlibApi
    private fun generateAndShowNewSchedule() {
        val scheduleActivity = requireActivity() as ScheduleActivity
        schedule = scheduler.schedule(scheduleActivity.posts.get(), scheduleActivity.guards.get())
        binding.generatedScheduleText.text = descriptionTextCreationFunc(schedule)
    }
}