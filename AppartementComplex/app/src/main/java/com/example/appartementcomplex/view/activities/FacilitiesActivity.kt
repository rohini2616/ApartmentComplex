package com.example.appartementcomplex.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.appartementcomplex.R
import com.example.appartementcomplex.db.AppPreferenceHelper
import com.example.appartementcomplex.view.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FacilitiesActivity : AppCompatActivity() {

    private lateinit var tabView: TabLayout
    private lateinit var viewpager: ViewPager2
    private lateinit var btnLogout: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facilities)

        btnLogout = findViewById(R.id.ivLogout)
        btnLogout.setOnClickListener {
            AppPreferenceHelper(this).clear()
            startActivity(Intent(this,LoginActivity::class.java))
            finish()
        }
        tabView = findViewById(R.id.tabContainer)
        viewpager = findViewById(R.id.vp_Fragment)
        viewpager.adapter = PagerAdapter(this)
        TabLayoutMediator(tabView, viewpager) { tab, position ->
            tab.text = (viewpager.adapter as PagerAdapter).getPageTitle(position)
        }.attach()
        tabView.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let { tabView.getTabAt(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
}
