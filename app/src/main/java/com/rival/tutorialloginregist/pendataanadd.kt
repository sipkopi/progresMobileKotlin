package com.rival.tutorialloginregist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class pendataanadd : AppCompatActivity() {
    val perlakuan = arrayOf("Penyiraman", "Pempupukan")
    val jmlhtnm = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pendataanadd)

        val spinnerPerlakuan = findViewById<Spinner>(R.id.Sp_perlakuan)
        val arrayAdapterPerlakuan = ArrayAdapter(this, android.R.layout.simple_spinner_item, perlakuan)
        spinnerPerlakuan.adapter = arrayAdapterPerlakuan
        spinnerPerlakuan.onItemSelectedListener = createOnItemSelectedListener("Perlakuan")

        val spinnerJmlhtnm = findViewById<Spinner>(R.id.Sp_jmlhtnm)
        val arrayAdapterJmlhtnm = ArrayAdapter(this, android.R.layout.simple_spinner_item, jmlhtnm)
        spinnerJmlhtnm.adapter = arrayAdapterJmlhtnm
        spinnerJmlhtnm.onItemSelectedListener = createOnItemSelectedListener("Jumlah Tanam")
    }

    private fun createOnItemSelectedListener(spinnerName: String): AdapterView.OnItemSelectedListener {
        return object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedValue = when (spinnerName) {
                    "Jumlah Tanaman" -> perlakuan[p2]
                    "Kebutuhan Air" -> jmlhtnm[p2]
                    else -> ""
                }
                Toast.makeText(this@pendataanadd, "Anda memilih $spinnerName: $selectedValue", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // Tidak ada yang dipilih
            }
        }
    }
}
