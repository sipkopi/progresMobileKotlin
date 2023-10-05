package com.rival.tutorialloginregist


import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var registerButton: TextView

    private lateinit var dbHelper: SQLiteOpenHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        usernameEditText = findViewById(R.id.edt_username)
        passwordEditText = findViewById(R.id.edt_password)
        loginButton = findViewById(R.id.btn_login)
        registerButton = findViewById(R.id.register_btn)


        dbHelper = DatabaseHelper(this)

        loginButton.setOnClickListener {
            val inputUsername = usernameEditText.text.toString()
            val inputPassword = passwordEditText.text.toString()

            if (login(inputUsername, inputPassword)) {
                // Login berhasil, tambahkan aksi yang sesuai di sini.
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            } else {
                // Login gagal
                Toast.makeText(this, "Username atau kata sandi salah", Toast.LENGTH_SHORT).show()
            }
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun login(username: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val columns = arrayOf("id")
        val selection = "username = ? AND password = ?"
        val selectionArgs = arrayOf(username, password)
        val cursor: Cursor = db.query("user", columns, selection, selectionArgs, null, null, null)
        val count = cursor.count
        cursor.close()
        return count > 0
    }
}
