package com.diplomado.practica2.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHelper (context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_RUTINA (id INTEGER PRIMARY KEY AUTOINCREMENT, tipo INT NOT NULL, dia INT NOT NULL, " +
                "ejercicio TEXT NOT NULL, series INT NOT NULL, repeticiones INT NOT NULL )")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE $TABLE_RUTINA")
        onCreate(p0)
    }

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "fit.com.diplomado.practica2.db"
        public const val TABLE_RUTINA = "rutina"
    }
}