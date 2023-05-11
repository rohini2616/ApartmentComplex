package com.example.appartementcomplex.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appartementcomplex.db.Facilities
import com.example.appartementcomplex.view.adapter.FacilitiesAdapter
import com.example.appartementcomplex.R
import com.example.appartementcomplex.db.LocalDb
import kotlinx.coroutines.launch


class FacilitiesFragment : Fragment() {

    private lateinit var rvData: RecyclerView
    private var list = ArrayList<Facilities>()
    private var facilitiesAdapter = FacilitiesAdapter()

    companion object {
        @JvmStatic
        fun newInstance() =
            FacilitiesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_facilities, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvData = view.findViewById(R.id.rvList)
        rvData.layoutManager = LinearLayoutManager(requireActivity())
        rvData.adapter = facilitiesAdapter
        lifecycleScope.launch {
            val facilities = LocalDb.getInstance(requireActivity()).facilityDao()
            val facilityList = facilities.getAllFacilities()
            if (facilityList?.size!! == 0) {
                val tempList = ArrayList<Facilities>()
                tempList.add(Facilities(1, "Clubhouse", "50","https://watermarkatthegrove.com/wp-content/uploads/2020/01/Watermark-at-Grove-poi-014-uai-2064x1378.jpg"))
                tempList.add(Facilities(2, "Tennis Court", "100", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS84tUwhsE8kqwAf31GseDlr_glOgTXXdZNEeLkPSYLJw&s"))
                facilities.insertAll(
                    tempList
                )
                list.addAll(facilities.getAllFacilities()!!)
                facilitiesAdapter.addItems(list)
            }else{
                list.addAll(facilities.getAllFacilities()!!)
                facilitiesAdapter.addItems(list)
            }
        }
    }


}