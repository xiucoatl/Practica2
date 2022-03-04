package com.diplomado.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.diplomado.practica2.databinding.ActivityInsertBinding
import com.diplomado.practica2.db.DbFit

class InsertActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityInsertBinding

    public var opc = 0 // spinner items

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinner: Spinner = binding.opcionesSpinner
        ArrayAdapter.createFromResource(
            this,
            R.array.opcionesSpinner, android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter=adapter
        }

        spinner.onItemSelectedListener = this



    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        opc = parent.getItemIdAtPosition(pos).toInt()

        mensaje(binding.opcionesSpinner.selectedItemId.toString())


        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    fun click(view: View) {
        val dbFit = DbFit(this)

        with(binding){



            if(!opcSpinnerDia.selectedItem.toString().isEmpty() && !tietEjercicio.text.toString().isEmpty() && !tietRepeticiones.text.toString().isEmpty() && !tietSeries.text.toString().isEmpty() && !opcionesSpinner.selectedItem.toString().isEmpty()) {
                val id = dbFit.insertRutina(
                    opcionesSpinner.selectedItemId.toString().toInt(),

                    opcSpinnerDia.selectedItemId.toString().toInt(),
                    tietEjercicio.text.toString(),
                    tietSeries.text.toString().toInt(),
                    tietRepeticiones.text.toString().toInt()
                )
                if (id > 0) {
                    mensaje("@string/sInOk")


                    tietEjercicio.setText("")
                    tietRepeticiones.setText("")
                    tietSeries.setText("")


                } else {
                    mensaje("@string/sInError")

                }
            }else{
                mensaje("@string/sInVal")}

        }

    }

    fun mensaje(mensaje:String){
        Toast.makeText(this@InsertActivity, mensaje, Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }


}