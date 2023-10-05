package com.rival.tutorialloginregist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "myapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USER = "user"

        // Kolom-kolom dalam tabel "user"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAMA = "name"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE $TABLE_USER ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_NAMA TEXT,"
                + "$COLUMN_USERNAME TEXT,"
               + "$COLUMN_PASSWORD TEXT,"
                + "$COLUMN_PHONE TEXT)")
        db.execSQL(CREATE_TABLE)

        // Isi contoh data untuk tabel user
        insertSampleUser(db,"admin","123admin","administrator","08123123122")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
//            if (oldVersion < 2) {
//                // Tambahkan kolom "name" jika versi sebelumnya kurang dari 2
//                db.execSQL("ALTER TABLE $TABLE_USER ADD COLUMN $COLUMN_NAMA TEXT")
//            }


    }

    private fun insertSampleUser(db: SQLiteDatabase, username: String, password: String,name : String,  phone: String) {
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)
      values.put(COLUMN_NAMA, name)
        values.put(COLUMN_PHONE, phone)
        db.insert(TABLE_USER, null, values)
    }
}
