package com.diplomado.practica2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.diplomado.practica2.databinding.ActivityDetailsBinding
import com.diplomado.practica2.db.DbFit
import com.diplomado.practica2.model.Fit

class Activity_details : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var dbFit: DbFit
    var fit: Fit? = null
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_details)

        if (savedInstanceState == null) {
            var bundle = intent.extras
            if (bundle != null) {
                id = bundle.getInt("ID", 0)
            }
        } else {
            id = savedInstanceState.getSerializable("ID") as Int
        }

        dbFit = DbFit(this)
        fit = dbFit.getRutina(id)

        if (fit != null) {
            with(binding) {
                var dia = fit?.dia.toString()
                //tietEjercicio.text = "@string/sEjercicio $fit?.ejercicio".toString()
                tietEjercicio.setText(fit?.ejercicio)
                tietRepeticiones.setText(fit?.reps.toString())
                tietSeries.setText(fit?.series.toString())
                var tipo = fit?.tipo.toString().toInt()
                tietTipo.inputType = InputType.TYPE_NULL
                tietSeries.inputType = InputType.TYPE_NULL
                tietRepeticiones.inputType = InputType.TYPE_NULL
                tietEjercicio.inputType = InputType.TYPE_NULL
                tietDia.inputType = InputType.TYPE_NULL

                when (dia.toString().toInt()) {
                    0 -> tietDia.setText(R.string.sD1)
                    1 -> tietDia.setText(R.string.sD2)
                    2 -> tietDia.setText(R.string.sD3)
                    3 -> tietDia.setText(R.string.sD4)
                    4 -> tietDia.setText(R.string.sD5)
                    5 -> tietDia.setText(R.string.sD6)
                    6 -> tietDia.setText(R.string.sD7)
                }

                when (tipo.toString().toInt()) {
                    0 -> tietTipo.setText(R.string.opc1)
                    1 -> tietTipo.setText(R.string.opc2)
                    2 -> tietTipo.setText(R.string.opc3)
                    3 -> tietTipo.setText(R.string.opc4)
                    4 -> tietTipo.setText(R.string.opc5)
                    5 -> tietTipo.setText(R.string.opc6)
                    6 -> tietTipo.setText(R.string.opc7)
                }
            }

        }
    }

    fun click(view: View) {
        when(view.id){
            R.id.btnEdit ->{
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
                finish()
            }
            R.id.btnDelete ->{
                AlertDialog.Builder(this)
                    .setTitle(R.string.sConfirmacion)
                    .setMessage(R.string.sMsgC ) //${fit?.tipo}?
                    .setPositiveButton("Sí", DialogInterface.OnClickListener { dialogInterface, i ->
                        if(dbFit.deleteRutina(id)){
                            Toast.makeText(this, R.string.sMsgBorrado, Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this, MainActivity::class.java))
                        }
                    })
                    .setNegativeButton("No", DialogInterface.OnClickListener { dialogInterface, i ->
                        //algo
                    })
                    .show()
            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}