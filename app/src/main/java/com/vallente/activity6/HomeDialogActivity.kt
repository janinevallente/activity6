package com.vallente.activity6

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.system.exitProcess

class HomeDialogActivity : AppCompatActivity() {

    private lateinit var viewSelectedTime: TextView
    private lateinit var viewSelectedDate: TextView
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_dialog)

        val alertDialogButton: Button = findViewById(R.id.alertDialogButton)
        val datePickerDialogButton: Button = findViewById(R.id.datePickerDialogButton)
        val timePickerDialogButton: Button = findViewById(R.id.timePickerDialogButton)
        viewSelectedTime = findViewById(R.id.viewSelectedTime)
        viewSelectedDate = findViewById(R.id.viewSelectedDate)

        alertDialogButton.setOnClickListener {
            showAlertDialog()
        }

        datePickerDialogButton.setOnClickListener {
            showDatePickerDialog()
        }

        timePickerDialogButton.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showTimePickerDialog() {
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                // Update the TextView with the selected time
                val selectedTime = formatTime(hourOfDay, minute)
                viewSelectedTime.text = selectedTime
            },
            currentHour,
            currentMinute,
            false // 24-hour format (true) or AM/PM (false)
        )

        timePickerDialog.show()
    }

    private fun formatTime(hour: Int, minute: Int): String {
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        val timeFormat = SimpleDateFormat("h:mm a", Locale.getDefault())
        return timeFormat.format(calendar.time)
    }

    private fun showDatePickerDialog() {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, day ->
                // Update the TextView with the selected date
                val selectedDate = "$year-${month + 1}-$day"
                viewSelectedDate.text = selectedDate
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun showAlertDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Exit")
        builder.setMessage("Are you sure you want to exit?")
        builder.setPositiveButton("Yes") { _, _ ->
            finishAffinity()
            exitProcess(0)
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}
