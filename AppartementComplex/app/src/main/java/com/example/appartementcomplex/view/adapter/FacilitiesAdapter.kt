package com.example.appartementcomplex.view.adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appartementcomplex.view.activities.BookingActivity
import com.example.appartementcomplex.R
import com.example.appartementcomplex.db.Facilities

class FacilitiesAdapter :
    RecyclerView.Adapter<FacilitiesAdapter.FacilitiesHolder>() {
    private var facilityList = ArrayList<Facilities>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FacilitiesHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.facilites_data, parent, false)
        return FacilitiesHolder(itemView)

    }

    override fun onBindViewHolder(holder: FacilitiesHolder, position: Int) {
        holder.bindFacilities(facilityList[position])
    }

    override fun getItemCount(): Int {
        return facilityList.size

    }

    fun addItems(list: ArrayList<Facilities>) {
        this.facilityList.clear()
        this.facilityList.addAll(list)
        notifyDataSetChanged()
    }

    class FacilitiesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgFacilities: ImageView = itemView.findViewById(R.id.imgPerson)
        private val facilitiesName: TextView = itemView.findViewById(R.id.tvFacilitiesName)
        private val facilitiesFees: TextView = itemView.findViewById(R.id.tvFees)
        private val btnBook: Button = itemView.findViewById(R.id.btnBook)


        fun bindFacilities(facilities: Facilities) {
            facilitiesName.text = facilities.facilitiesName
            facilitiesFees.text = facilities.bookingAmount
            Glide.with(itemView.context).load(facilities.urlImage)
                .into(imgFacilities)
            btnBook.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, BookingActivity::class.java)
                intent.putExtra("NAME",facilities.facilitiesName)
                intent.putExtra("AMOUNT",facilities.bookingAmount)
                context.startActivity(intent)

            }

        }

    }
}