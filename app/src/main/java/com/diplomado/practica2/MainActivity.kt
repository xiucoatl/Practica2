package com.diplomado.practica2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.diplomado.practica2.adapter.FitAdapter
import com.diplomado.practica2.databinding.ActivityDetailsBinding
import com.diplomado.practica2.databinding.ActivityMainBinding
import com.diplomado.practica2.db.DbFit
import com.diplomado.practica2.model.Fit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listFit: ArrayList<Fit>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        val dbFit = DbFit(this)
        listFit = dbFit.getRutina()

        if(listFit.size == 0) binding.tvSinRutina.visibility = View.VISIBLE
        else binding.tvSinRutina.visibility = View.INVISIBLE

        val fitAdapter = FitAdapter(this, listFit)
        binding.lvfit.adapter = fitAdapter

        binding.lvfit.setOnItemClickListener{ adapterView, view, i, l ->
            val intent = Intent(this, Activity_details::class.java)
            intent.putExtra("ID", l.toInt())

            startActivity(intent)
            finish()
        }

    }

    fun click(view: View) {
        startActivity(Intent(this, InsertActivity::class.java))
        finish()
    }
}