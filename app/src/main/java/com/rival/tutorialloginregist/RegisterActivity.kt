package com.rival.tutorialloginregist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rival.tutorialloginregist.MainActivity
import com.rival.tutorialloginregist.databinding.ActivityRegisterBinding  // Impor View Binding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding  // Inisialisasi View Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.loginBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
