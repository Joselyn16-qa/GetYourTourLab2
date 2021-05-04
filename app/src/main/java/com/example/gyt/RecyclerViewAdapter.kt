package com.example.gyt


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter:RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> (){


    private val nameTour = arrayOf("Corea del Sur","Italia", "Irlanda", "Mexico")
    private val infoTour = arrayOf("Desde $40","Desde $70", "Desde $50", "Desde $30")

    private val imageTour= intArrayOf(
        R.drawable.image_1,
        R.drawable.image_2,
        R.drawable.image_3,
        R.drawable.image_4
    )


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var infoTour : TextView
        var nameTour : TextView
        var ratingTour : RatingBar
        var imageTour : ImageView

        init{
            imageTour = itemView.findViewById(R.id.imageTour)
            nameTour = itemView.findViewById(R.id.nameTour)
            ratingTour = itemView.findViewById(R.id.ratingTour)
            infoTour = itemView.findViewById(R.id.infoTour)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_cardview,parent,false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameTour.text=nameTour[position]
        holder.infoTour.text=infoTour[position]
        holder.imageTour.setImageResource((imageTour[position]))

    }

    override fun getItemCount(): Int {
        return nameTour.size
    }
}