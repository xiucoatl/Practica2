package com.diplomado.practica2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.diplomado.practica2.R
import com.diplomado.practica2.databinding.ListItemBinding
import com.diplomado.practica2.model.Fit

class FitAdapter(contexto: Context, listRutina: ArrayList<Fit>): BaseAdapter() {
    private val listRutina = listRutina
    private val layoutInflater = LayoutInflater.from(contexto)

    override fun getCount(): Int{
        return listRutina.size
    }

    override fun getItem(p0: Int): Any {
        return listRutina[p0]
    }

    override fun getItemId(p0: Int): Long {
        return listRutina[p0].id.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ListItemBinding.inflate(layoutInflater)
        with(binding){
            tvDescEjercicio.text = listRutina[p0].ejercicio
            tvRepeticion.text = listRutina[p0].reps.toString()

            tvSerie.text = listRutina[p0].series.toString()

            when (listRutina[p0].dia){
                0 -> tvDia.setText(R.string.sD1)
                1 -> tvDia.setText(R.string.sD2)
                2 -> tvDia.setText(R.string.sD3)
                3 -> tvDia.setText(R.string.sD4)
                4 -> tvDia.setText(R.string.sD5)
                5 -> tvDia.setText(R.string.sD6)
                6 -> tvDia.setText(R.string.sD7)
            }

            when (listRutina[p0].tipo){
                0 -> {tvTipo.setText(R.string.opc1)
                    imageView.setImageResource(R.drawable.chest)
                }
                1 -> {tvTipo.setText(R.string.opc2)
                    imageView.setImageResource(R.drawable.espalda)
                }
                2 -> {tvTipo.setText(R.string.opc3)
                    imageView.setImageResource(R.drawable.biceps)
                }
                3 -> {tvTipo.setText(R.string.opc4)
                    imageView.setImageResource(R.drawable.triceps)
                }
                4 -> {tvTipo.setText(R.string.opc5)
                    imageView.setImageResource(R.drawable.abs)
                }
                5 -> {tvTipo.setText(R.string.opc6)
                    imageView.setImageResource(R.drawable.leg)
                }
                6 -> {tvTipo.setText(R.string.opc7)
                    imageView.setImageResource(R.drawable.hombro)
                }

            }

            //opcionesSpinner.selectedItem = listRutina[p0].tipo
            //opcionesSpinnerDia.selectedItem = listRutina[p0].dia
        }
        return binding.root
    }

}