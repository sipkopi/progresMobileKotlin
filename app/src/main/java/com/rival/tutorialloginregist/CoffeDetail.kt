package com.rival.tutorialloginregist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class CoffeDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coffe_detail, container, false)

        val detailDesc = view.findViewById<TextView>(R.id.detailDesc)
        val detailTitle = view.findViewById<TextView>(R.id.detailTitle)
        val detailImage = view.findViewById<ImageView>(R.id.detailImage)

        val bundle = arguments
        if (bundle != null) {
            detailDesc.text = bundle.getString("Desc")
            detailImage.setImageResource(bundle.getInt("Image"))
            detailTitle.text = bundle.getString("Title")
        }

        return view
    }
}
