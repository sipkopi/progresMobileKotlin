package com.rival.tutorialloginregist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CoffeAdapter(private val CoffeData: Array<CoffeData>, private val context: Context) :
    RecyclerView.Adapter<CoffeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.coffe_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val myMovieDataList = CoffeData[position]
        holder.textViewName.text = myMovieDataList.getMovieName()
        holder.textViewDate.text = myMovieDataList.getMovieDate()
        holder.movieImage.setImageResource(myMovieDataList.getMovieImage())

        holder.itemView.setOnClickListener {
            Toast.makeText(context, myMovieDataList.getMovieName(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return CoffeData.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val movieImage: ImageView = itemView.findViewById(R.id.imageview)
        val textViewName: TextView = itemView.findViewById(R.id.textName)
        val textViewDate: TextView = itemView.findViewById(R.id.textdate)
    }
}