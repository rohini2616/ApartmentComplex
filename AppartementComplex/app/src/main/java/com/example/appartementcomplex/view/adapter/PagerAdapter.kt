package com.example.appartementcomplex.view.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.appartementcomplex.view.fragments.BookingHistoryFragment
import com.example.appartementcomplex.view.fragments.FacilitiesFragment

class PagerAdapter(fragmentManager: AppCompatActivity) :
    FragmentStateAdapter(fragmentManager) {
    var list = listOf(
        "Facilities", "Booking History"
    )

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FacilitiesFragment.newInstance()
            1 -> BookingHistoryFragment.newInstance()
            else -> Fragment()
        }
    }

    fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> list[0]
            1 -> list[1]
            else -> "UnKnown"
        }
    }

}