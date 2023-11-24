package com.misfinanzaspersonales

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.misfinanzaspersonales.adapters.ConceptoListAdapter
import com.misfinanzaspersonales.models.MisFinanzasPersonalesApplication
import com.misfinanzaspersonales.models.entities.ConceptoEntity
import com.misfinanzaspersonales.viewmodels.ConceptoViewModel
import com.misfinanzaspersonales.viewmodels.ConceptoViewModelFactory
import com.misfinanzaspersonales.views.NuevoConceptoActivity


class MainActivity: AppCompatActivity() {
    //private var concepto: String = ""
    //private var fecha_y_hora:String =""

    private val nuevoConceptoActivityRequestCode = 1
    private val mConceptoViewModel: ConceptoViewModel by viewModels {
        ConceptoViewModelFactory((application as MisFinanzasPersonalesApplication).mConceptoRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ConceptoListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // ->>> aqui se rebienta la app <<<-
        mConceptoViewModel.obtenerTodosLosConceptosASC.observe(this) { conceptos ->
            conceptos.let { adapter.submitList(it) }
        }

/*
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NuevoConceptoActivity::class.java)
            startActivityForResult(intent, nuevoConceptoActivityRequestCode)
        }

 */
    }
/*
    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == nuevoConceptoActivityRequestCode && resultCode == Activity.RESULT_OK) {

            intentData?.getStringExtra(NuevoConceptoActivity.EXTRA_REPLY_CONCEPTO)?.let { reply ->
                //concepto = reply
            }

            intentData?.getStringExtra(NuevoConceptoActivity.EXTRA_REPLY_FECHA_Y_HORA)?.let { reply ->
                //fecha_y_hora = reply
            }

            //Log.d("TAG-2", concepto)
            //Log.d("TAG-2", fecha_y_hora)

            //val mConceptoEntity = ConceptoEntity(concepto,fecha_y_hora)

            //mConceptoViewModel.insertarComcepto(mConceptoEntity)

        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()
        }

    }
 */
}