package com.example.appartementcomplex.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.appartementcomplex.R
import com.example.appartementcomplex.model.TimeSlot

class TimeSlotAdapter(var onTimeClickListener: TimeClickListener) :
    RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {
    private var bookingList = ArrayList<String>()

    private var selectedItem = -1

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TimeSlotViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.batmetan_booking_time, parent, false)
        return TimeSlotViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        val item = bookingList[position]
        holder.bindFacilities(item)
        holder.itemView.setOnClickListener {
            onTimeClickListener.onTimeClick(item)

            if (selectedItem != position) {
                val previousSelected = selectedItem
                selectedItem = position
                notifyItemChanged(previousSelected)
                notifyItemChanged(selectedItem)
            }
            notifyDataSetChanged()
        }

        if (selectedItem == position) {
            holder.container.setBackgroundResource(
                R.drawable.selected_bg
            )
        } else {
            holder.container.setBackgroundResource(
                R.drawable.bookbg
            )
        }
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }

    fun addItems(list: ArrayList<String>) {
        this.bookingList.clear()
        this.bookingList.addAll(list)
        notifyDataSetChanged()
    }


    class TimeSlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvTimeHours: TextView = itemView.findViewById(R.id.tvTimeHours)
        val container: ConstraintLayout = itemView.findViewById(R.id.container)

        fun bindFacilities(booking: String) {
            tvTimeHours.text = booking
        }
    }


    interface TimeClickListener {
        fun onTimeClick(time: String)
    }
}