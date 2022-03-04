package com.diplomado.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.diplomado.practica2.databinding.ActivityDetailsBinding
import com.diplomado.practica2.databinding.ActivityEditBinding
import com.diplomado.practica2.db.DbFit
import com.diplomado.practica2.model.Fit

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var dbFit: DbFit
    var fit: Fit? = null
    var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState == null){
            val bundle = intent.extras
            if(bundle != null){
                id = bundle.getInt("ID", 0)
            }
        }else{
            id = savedInstanceState.getSerializable("ID") as Int
        }

        dbFit = DbFit(this)
        fit = dbFit.getRutina(id)

        if(fit != null){
            with(binding){
                opcionesSpinner.setSelection(fit?.tipo.toString().toInt())
                opcSpinnerDia.setSelection(fit?.dia.toString().toInt())
                //tietDia.setText(fit?.dia.toString())
                tietEjercicio.setText(fit?.ejercicio)
                tietRepeticiones.setText(fit?.reps.toString())
                tietSeries.setText(fit?.series.toString())
                //tietTipo.setText(fit?.tipo.toString())

            }
        }

    }

    fun click(view: View) {
        with(binding){
            if(!tietEjercicio.text.toString().isEmpty() && !tietRepeticiones.text.toString().isEmpty() && !tietSeries.text.toString().isEmpty())
                if(dbFit.updateRutina(id, opcionesSpinner.selectedItemId.toString().toInt(), opcSpinnerDia.selectedItemId.toString().toInt(), tietEjercicio.text.toString(), tietSeries.text.toString().toInt(), tietRepeticiones.text.toString().toInt())){
                    Toast.makeText(this@EditActivity, R.string.sUpOk, Toast.LENGTH_LONG).show()
                    val intent = Intent(this@EditActivity, Activity_details::class.java)
                    intent.putExtra("ID", id)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this@EditActivity, R.string.sUpError, Toast.LENGTH_LONG).show()
                }
            else{
                Toast.makeText(this@EditActivity, R.string.sUpVal, Toast.LENGTH_LONG).show()}
        }

    }
}