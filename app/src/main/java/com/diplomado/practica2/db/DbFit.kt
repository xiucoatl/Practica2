package com.diplomado.practica2.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.diplomado.practica2.db.DBHelper.Companion.TABLE_RUTINA
import com.diplomado.practica2.model.Fit

class DbFit(context: Context?) {

    val context = context

    fun insertRutina(tipo: Int, dia: Int, ejercicio: String, series: Int, repeticiones: Int): Long{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var id: Long = 0

        try {
            val values = ContentValues()
            values.put("tipo", tipo)
            values.put("dia", dia)
            values.put("ejercicio", ejercicio)
            values.put("series", series)
            values.put("repeticiones", repeticiones)

            id= db.insert(TABLE_RUTINA, null, values)
        }catch(e: Exception){

        }finally {
            db.close()
        }
        return id

    }

    fun getRutina(): ArrayList<Fit>{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var listRutina = ArrayList<Fit>()
        var rutinaTmp: Fit? = null
        var cursorFit: Cursor? = null

        cursorFit = db.rawQuery("SELECT * FROM $TABLE_RUTINA ORDER BY DIA", null)

        if(cursorFit.moveToFirst()){
            do{
                rutinaTmp = Fit(cursorFit.getInt(0), cursorFit.getInt(1), cursorFit.getInt(2), cursorFit.getString(3), cursorFit.getInt(4), cursorFit.getInt(5))
                listRutina.add(rutinaTmp)
            }while(cursorFit.moveToNext())
        }
        cursorFit.close()
        return listRutina
    }//fin getRutina

    fun getRutina(id: Int): Fit?{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var fit : Fit? = null
        var cursorFit: Cursor? = null

        cursorFit = db.rawQuery("SELECT * FROM $TABLE_RUTINA WHERE ID = $id LIMIT 1 ", null)

        if(cursorFit.moveToFirst()){
            fit = Fit(cursorFit.getInt(0), cursorFit.getInt(1), cursorFit.getInt(2), cursorFit.getString(3), cursorFit.getInt(4), cursorFit.getInt(5))
        }
        cursorFit.close()
        return fit
    }//fin getRutina

    fun updateRutina (id: Int, tipo: Int, dia: Int, ejercicio: String, series: Int, repeticiones: Int): Boolean{

        var banderaOk = false
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try {
            db.execSQL("UPDATE $TABLE_RUTINA SET TIPO = '$tipo', DIA = '$dia', EJERCICIO='$ejercicio', SERIES = '$series', REPETICIONES='$repeticiones' WHERE id = $id")
            banderaOk = true

        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaOk

    }//fin update

    fun deleteRutina(id:Int): Boolean{
        var banderaOk = true
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("DELETE FROM $TABLE_RUTINA WHERE id = $id")
            banderaOk = true
        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaOk
    }// fin delete

}