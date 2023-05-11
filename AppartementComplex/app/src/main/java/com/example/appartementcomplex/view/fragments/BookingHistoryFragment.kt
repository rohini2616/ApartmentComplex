package com.example.appartementcomplex.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appartementcomplex.R
import com.example.appartementcomplex.db.Booking
import com.example.appartementcomplex.db.LocalDb
import com.example.appartementcomplex.view.adapter.BookingHistoryAdapter
import kotlinx.coroutines.launch


class BookingHistoryFragment : Fragment() {

    private lateinit var rvList: RecyclerView
    private lateinit var adapter: BookingHistoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_booking_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = BookingHistoryAdapter()
        rvList = view.findViewById(R.id.rvBookingHistory)
        rvList.layoutManager = LinearLayoutManager(requireActivity())
        rvList.adapter = adapter
        getHistory()
    }

    private fun getHistory() {
        lifecycleScope.launch {
            LocalDb.getInstance(requireActivity()).bookingDao().getAllBooking().collect {
                adapter.addItems(it as ArrayList<Booking>)
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BookingHistoryFragment()
    }
}