package com.talido.samalto.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.DatePicker
import android.widget.TimePicker
import com.google.android.material.textfield.TextInputEditText
import com.talido.samalto.model.data.toLocalTimeString
import java.util.*

fun interface OnDateTimeSetListener {
    /**
     * Called when the date and time has been set.
     * The Calendar will have the selected year, month, day, hour and minute.
     */
    fun onDateTimeSet(view: View, calendar: Calendar)
}

/**
 * An non-editable edittext, that open a date and time picker dialogs on click.
 * The text after the pick will default to mm:ss, can be overridden in the onDateTimeSetListener.
 */
class DateTimeEditText(context: Context, attrs: AttributeSet) : TextInputEditText(context, attrs),
    DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private var dateTimeSetListener: OnDateTimeSetListener? = null

    // Holds initial date and time the pickers will have. If not set, defaults to time at picking.
    var initialPickerTime: Calendar? = null

    private var selectedTime: Calendar = Calendar.getInstance()

    init {
        isFocusable = false
        setOnClickListener {
            startDateTimePicker()
        }
    }

    private fun startDateTimePicker() {
        val initialDate = initialPickerTime ?: Calendar.getInstance()
        DatePickerDialog(
            context,
            this,
            initialDate[Calendar.YEAR],
            initialDate[Calendar.MONTH],
            initialDate[Calendar.DAY_OF_MONTH]
        ).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val initialTime = initialPickerTime ?: Calendar.getInstance()
        selectedTime = Calendar.getInstance()
        selectedTime.set(Calendar.YEAR, year)
        selectedTime.set(Calendar.MONTH, month)
        selectedTime.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        TimePickerDialog(
            context,
            this,
            initialTime[Calendar.HOUR_OF_DAY],
            initialTime[Calendar.MINUTE],
            true
        ).show()
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        selectedTime.set(Calendar.HOUR_OF_DAY, hourOfDay)
        selectedTime.set(Calendar.MINUTE, minute)
        onDateTimeSet()
    }

    private fun onDateTimeSet() {
        setText(selectedTime.toLocalTimeString())
        dateTimeSetListener?.onDateTimeSet(this, selectedTime)
    }

    fun setOnDateTimeSetListener(listener: OnDateTimeSetListener) {
        dateTimeSetListener = listener
    }
}