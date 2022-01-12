package com.talido.samalto.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.talido.samalto.R
import com.talido.samalto.databinding.FragmentGuardsAmountBinding
import com.talido.samalto.model.data.Guard
import com.talido.samalto.view.ScheduleActivity
import java.lang.Integer.min
import java.util.*

class GuardsAmountFragment : Fragment() {
    private lateinit var binding: FragmentGuardsAmountBinding
    private var guardsAmount: Optional<Int> = Optional.empty()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGuardsAmountBinding.inflate(inflater, container, false)

        binding.guardAmountInput.doAfterTextChanged { text ->
            if (text!!.isEmpty()) {
                binding.next.isEnabled = false
                binding.next.setTextColor(resources.getColor(R.color.textColorDisabled))
            } else {
                try {
                    guardsAmount = Optional.of(min(text.toString().toInt(), 21))
                    setGuardNamesGridCellAmount(guardsAmount.get())
                    binding.next.isEnabled = true
                    binding.next.setTextColor(resources.getColor(R.color.textColorPrimary))
                } catch (ignored: NumberFormatException) { }
            }
        }

        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.next.setOnClickListener {
            // Get all valid guards
            val guards = mutableListOf<Guard>()
            for (view in binding.guardNamesGrid.children) {
                val textInputLayout = view as TextInputLayout
                val textInputEditText = textInputLayout.findViewById<TextInputEditText>(R.id.guardName)
                if (textInputEditText.text!!.isEmpty() ||
                    guards.map { it.name }.contains(textInputEditText.text.toString())) {
                    textInputLayout.error = "fill me!"
                } else {
                    guards.add(Guard(textInputEditText.text.toString()))
                    textInputLayout.error = null
                }
            }
            // If all are valid, save and continue
            if (guards.size == guardsAmount.get()) {
                (activity as ScheduleActivity).guards = Optional.of(guards)
                findNavController().navigate(GuardsAmountFragmentDirections.actionGuardsAmountFragmentToShowGeneratedScheduleFragment())
            }
        }

        return binding.root
    }

    override fun onStart() {
        (activity as ScheduleActivity).guards.ifPresent {
            guardsAmount = Optional.of(it.size)
            binding.guardAmountInput.setText(it.size.toString())
            setGuardNamesGridCellAmount(it.size)
            setGuardNamesGridValues(it)
            binding.next.isEnabled = true
            binding.next.setTextColor(resources.getColor(R.color.textColorPrimary))
        }
        super.onStart()
    }

    private fun setGuardNamesGridValues(guards: List<Guard>) {
        for ((index, view) in binding.guardNamesGrid.children.withIndex()) {
            val textInputLayout = view as TextInputLayout
            val textInputEditText = textInputLayout.findViewById<TextInputEditText>(R.id.guardName)
            textInputEditText.setText(guards[index].name)
        }
    }

    private fun setGuardNamesGridCellAmount(cellCount: Int) {
        if (cellCount > binding.guardNamesGrid.childCount) {
            for (i in binding.guardNamesGrid.childCount until cellCount) {
                View.inflate(
                    requireContext(),
                    R.layout.guard_name,
                    binding.guardNamesGrid
                )
            }
        }
        if (cellCount < binding.guardNamesGrid.childCount) {
            binding.guardNamesGrid.removeViews(
                cellCount,
                binding.guardNamesGrid.childCount - cellCount
            )
        }
    }
}