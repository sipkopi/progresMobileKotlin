package com.rival.tutorialloginregist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView
import java.util.ArrayList
import androidx.fragment.app.FragmentTransaction

class Home : Fragment() {
    private lateinit var txt_Seeall : TextView
    private lateinit var usernameTextView: TextView
    private lateinit var adapter: RecyclerView.Adapter<*>
    private lateinit var recyclerViewList: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // Mengambil TextView dari layout
        usernameTextView = view.findViewById(R.id.txt_session)

        // Mengambil nama pengguna dari SessionManager
        val sessionManager = SessionManager(requireContext())
        val username = sessionManager.getUsername()
        txt_Seeall = view.findViewById(R.id.txt_seeall)
        txt_Seeall.setOnClickListener {
            // Create an instance of Fragment2 (the destination fragment)
            val fragment2 = Profile()

            // Get the FragmentManager
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager

            // Start a new FragmentTransaction
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()

            // Replace the current fragment (Fragment1) with Fragment2
            transaction.replace(R.id.recView, fragment2)
            transaction.addToBackStack(null) // Add to the back stack (optional)

            // Commit the transaction
            transaction.commit()
        }
        txt_Seeall = view.findViewById(R.id.txt_seeall)

        // Tambahkan onClickListener untuk TextView
        txt_Seeall.setOnClickListener {
            val intent = Intent(activity, NotifikasiActivity::class.java)
            startActivity(intent)
        }

        if (username != null) {
            // Menampilkan nama pengguna di TextView
            usernameTextView.text = "Selamat Datang, $username"
        }


        recyclerViewList = view.findViewById(R.id.view)
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerViewList.layoutManager = linearLayoutManager
        val news = ArrayList<ListDomain>()
        news.add(ListDomain("Arabica Coffe", "kopi1"))
        news.add(ListDomain("Robusta Coffe", "kopi3"))
        news.add(ListDomain("Liberica Coffe", "kopi1"))
        news.add(ListDomain("Arabica Coffe", "kopi3"))

        adapter = NewsAdapter(news)
        recyclerViewList.adapter = adapter



        return view
    }

}
