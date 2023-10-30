package com.rival.tutorialloginregist


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.rival.tutorialloginregist.Scan.coffe

class RecAadapter(private val context : Context, private val coffeList : ArrayList<coffe>) : RecyclerView.Adapter<RecAadapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val imgTitle: ImageView = itemView.findViewById(R.id.imgTitle)
        val kodeKopi: TextView = itemView.findViewById(R.id.tvName)
        val kodePohon: TextView = itemView.findViewById(R.id.tvIng)
        val constraint_row : ConstraintLayout = itemView.findViewById(R.id.constraint_row)
        val cardView : CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = coffeList[position]

        holder.imgTitle.setImageResource(currentItem.img_1)
        holder.kodeKopi.text = currentItem.kode_kopi
        holder.kodePohon.text = currentItem.kode_pohon

        val cont = holder.constraint_row.context
        holder.constraint_row.setOnClickListener {
            val intent = Intent(it.context, SecondActivity::class.java)

            intent.putExtra("image",currentItem.kode_kopi)
            intent.putExtra("title",currentItem.kode_kopi)
            intent.putExtra("ingredients",currentItem.kode_pohon)

            it.context.startActivity(intent)

            Toast.makeText(cont, "the item ${currentItem.kode_kopi} is clicked", Toast.LENGTH_SHORT).show()
        }


        holder.constraint_row.setOnLongClickListener(View.OnLongClickListener {
            Toast.makeText(cont, "the item ${currentItem.kode_kopi} is long clicked", Toast.LENGTH_SHORT).show()

            return@OnLongClickListener true
        })


        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.cardView.context, R.anim.scale_up))

    }

    override fun getItemCount(): Int {
        return coffeList.size
    }
}