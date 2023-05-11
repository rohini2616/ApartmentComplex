package com.example.appartementcomplex.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appartementcomplex.R
import com.example.appartementcomplex.db.Booking

class BookingHistoryAdapter :
    RecyclerView.Adapter<BookingHistoryAdapter.BookingHistoryViewHolder>() {

    private var bookingList = ArrayList<Booking>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BookingHistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.booking_history_row, parent, false)
        return BookingHistoryViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: BookingHistoryViewHolder, position: Int) {
        val item = bookingList[position]
        holder.bindBookings(item)
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }

    fun addItems(list: ArrayList<Booking>) {
        this.bookingList.clear()
        this.bookingList.addAll(list)
        notifyDataSetChanged()
    }


    class BookingHistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.tvTitleName)
        val tvAmount: TextView = itemView.findViewById(R.id.tvAmount)
        val tvDate: TextView = itemView.findViewById(R.id.tvDateSlot)
        val tvStatus: TextView = itemView.findViewById(R.id.tvBookingStatus)

        fun bindBookings(booking: Booking) {
            tvTitle.text = booking.name
            tvAmount.text = booking.amount
            tvDate.text = booking.dateTimeString
            if (booking.status) {
                tvStatus.text = "Booked"
            } else {
                tvStatus.text = "Not Booked"
            }

        }
    }

}