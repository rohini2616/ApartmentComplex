package com.example.appartementcomplex.view.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appartementcomplex.R
import com.example.appartementcomplex.db.Booking
import com.example.appartementcomplex.db.LocalDb
import com.example.appartementcomplex.db.Util
import com.example.appartementcomplex.view.adapter.TimeSlotAdapter
import java.time.LocalDate
import java.util.*

class BookingActivity : AppCompatActivity(), TimeSlotAdapter.TimeClickListener {

    private lateinit var title: TextView
    private lateinit var rvTimeData: RecyclerView
    private lateinit var timeSlotAdapter: TimeSlotAdapter
    private lateinit var datePicker: DatePicker
    private var selectedDate = LocalDate.now()
    private var selectedSlotTime = ""
    private var titleString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        titleString = intent.getStringExtra("NAME").toString()
        title = findViewById(R.id.tvBookingTitle)
        title.text = titleString
        setUpDatePicker()
        setUpList()
    }

    private fun setUpList() {
        timeSlotAdapter = TimeSlotAdapter(this)
        rvTimeData.layoutManager = GridLayoutManager(this, 3)
        rvTimeData.adapter = timeSlotAdapter
        val tempList = ArrayList<String>()
        tempList.add("10AM-11AM")
        tempList.add("11AM-12PM")
        tempList.add("12AM-13PM")
        tempList.add("13PM-14PM")
        tempList.add("14PM-15PM")
        tempList.add("15AM-16PM")
        tempList.add("16PM-17PM")
        tempList.add("17PM-18PM")
        tempList.add("18PM-19PM")
        tempList.add("19PM-20PM")
        tempList.add("20PM-21PM")
        tempList.add("21PM-22PM")
        timeSlotAdapter.addItems(tempList)

    }

    private fun setUpDatePicker() {
        datePicker = findViewById(R.id.datePicker)
        val today = Calendar.getInstance()
        datePicker.minDate = today.timeInMillis
        rvTimeData = findViewById(R.id.rvtimeList)
        datePicker.setOnDateChangedListener { datePicker, year, month, dayOfMonth ->
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
        }
    }

    override fun onTimeClick(time: String) {
        selectedSlotTime = time
        showDetails()
    }

    private fun showDetails() {

        val alert = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this).inflate(R.layout.booking_details, null)
        val tvTitle: TextView = view.findViewById(R.id.tvTitleName)
        val tvAmount: TextView = view.findViewById(R.id.tvAmount)
        val tvDate: TextView = view.findViewById(R.id.tvDateSlot)
        tvTitle.text = titleString
        tvDate.text = "$selectedDate $selectedSlotTime"

        if (titleString == "Clubhouse") {
            tvAmount.text = "Amount --- 50/hour"
        } else if (titleString == "Tennis Court") {
            val time =
                Util.convertDate(selectedSlotTime.substringBefore("-"), selectedDate.toString())


            val hour =  Util.convertToTime(time).toInt()
            if (hour>16) {
                tvAmount.text = "Amount --- 500/hour"
            }else{
                tvAmount.text = "Amount --- 100/hour"
            }
        }

        val btnConfirm: TextView = view.findViewById(R.id.btnBook)

        btnConfirm.setOnClickListener {
            bookSlot(tvAmount.text.toString())
        }
        alert.setView(view)
        alert.show()
    }

    private fun bookSlot(amount: String) {
        if (selectedSlotTime.isNotEmpty()) {

            val dateString = "$selectedDate $selectedSlotTime"
            val endTime =
                Util.convertDate(selectedSlotTime.substringAfter("-"), selectedDate.toString())
            val startTime =
                Util.convertDate(selectedSlotTime.substringBefore("-"), selectedDate.toString())

            val check = LocalDb.getInstance(this).bookingDao()
                .checkBookingStatus(titleString, true, startTime, endTime)
            if (check == null) {
                LocalDb.getInstance(this).bookingDao().insertBooking(
                    Booking(
                        0,
                        titleString,
                        startTime,
                        endTime,
                        dateString,
                        amount,
                        true
                    )
                )
                Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
                finish()
            } else {
                Toast.makeText(this, "Slot is already booked", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(this, "Please select slot", Toast.LENGTH_LONG).show()
        }
    }


}
