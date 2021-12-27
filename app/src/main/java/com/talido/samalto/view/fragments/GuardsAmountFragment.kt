package com.talido.samalto.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        guardsAmount.ifPresent {
            binding.next.isEnabled = true
            binding.next.setTextColor(resources.getColor(R.color.textColorPrimary))
        }
        binding.guardAmountInput.doOnTextChanged { text, _, _, _ ->
            if (text!!.isEmpty()) {
                binding.next.isEnabled = false
                binding.next.setTextColor(resources.getColor(R.color.textColorDisabled))
            } else {
                setGuardNamesGridAccordingToGuardsAmount(text)
            }
        }

        binding.prev.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.next.setOnClickListener {
            val guards = mutableListOf<Guard>()
            for (view in binding.guardNamesGrid.children) {
                val textInputLayout = view as TextInputLayout
                val textInputEditText =
                    (textInputLayout.getChildAt(0) as FrameLayout).getChildAt(0) as TextView
                if (textInputEditText.text!!.isEmpty() || guards.map { it.name }.contains(textInputEditText.text!!.toString())) {
                    textInputLayout.error = "fill me!"
                } else {
                    guards.add(Guard(textInputEditText.text!!.toString()))
                    textInputLayout.error = null
                }
            }
            if (guards.size == guardsAmount.get()) {
                (activity as ScheduleActivity).guards = Optional.of(guards)
                findNavController().navigate(GuardsAmountFragmentDirections.actionGuardsAmountFragmentToShowGeneratedScheduleFragment())
            }
        }

        return binding.root
    }

    private fun setGuardNamesGridAccordingToGuardsAmount(text: CharSequence) {
        try {
            guardsAmount = Optional.of(min(text.toString().toInt(), 21))

            if (guardsAmount.get() > binding.guardNamesGrid.childCount) {
                for (i in binding.guardNamesGrid.childCount until guardsAmount.get()) {
                    View.inflate(
                        requireContext(),
                        R.layout.guard_name,
                        binding.guardNamesGrid
                    )
                }
            }
            if (guardsAmount.get() < binding.guardNamesGrid.childCount) {
                binding.guardNamesGrid.removeViews(
                    guardsAmount.get(),
                    binding.guardNamesGrid.childCount - guardsAmount.get()
                )
            }

            binding.next.isEnabled = true
            binding.next.setTextColor(resources.getColor(R.color.textColorPrimary))
        } catch (ignored: NumberFormatException) {
        }
    }
}