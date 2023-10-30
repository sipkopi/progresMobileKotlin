package com.rival.tutorialloginregist
import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.rival.tutorialloginregist.R

import com.rival.tutorialloginregist.channelID
import com.rival.tutorialloginregist.messageExtra
import com.rival.tutorialloginregist.notificationID
import com.rival.tutorialloginregist.titleExtra
import java.util.*

class SettingFragment : Fragment() {

    private lateinit var titleEditText: EditText
    private lateinit var messageEditText: EditText
    private lateinit var timePicker: TimePicker
    private lateinit var datePicker: DatePicker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        titleEditText = view.findViewById(R.id.titleET)
        messageEditText = view.findViewById(R.id.messageET)
        timePicker = view.findViewById(R.id.timePicker)
        datePicker = view.findViewById(R.id.datePicker)

        val submitButton = view.findViewById<Button>(R.id.submitButton)
        submitButton.setOnClickListener { scheduleNotification() }

        createNotificationChannel()

        return view
    }

    private fun scheduleNotification() {
        val intent = Intent(requireContext(), Notification::class.java)
        val title = titleEditText.text.toString()
        val message = messageEditText.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            requireContext(),
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val canScheduleExactAlarms = alarmManager.canScheduleExactAlarms()
            if (canScheduleExactAlarms) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    time,
                    pendingIntent
                )
                showAlert(time, title, message)
            } else {
                // Handle the case where scheduling exact alarms is not permitted
                // You might want to use setExact instead, or inform the user about the limitation.
            }
        } else {
            // For versions prior to Android 12 (API 31), use setExact directly
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent)
            showAlert(time, title, message)
        }
    }

    private fun showAlert(time: Long, title: String, message: String) {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(requireContext())
        val timeFormat = android.text.format.DateFormat.getTimeFormat(requireContext())

        AlertDialog.Builder(requireContext())
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: $title\nMessage: $message\nAt: ${dateFormat.format(date)} ${timeFormat.format(date)}"
            )
            .setPositiveButton("Okay") { _, _ -> }
            .show()
    }

    private fun getTime(): Long {
        val minute = timePicker.minute
        val hour = timePicker.hour
        val day = datePicker.dayOfMonth
        val month = datePicker.month
        val year = datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}
