package com.misfinanzaspersonales.views

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.misfinanzaspersonales.R
import java.time.Instant

class NuevoConceptoActivity : AppCompatActivity() {

    private lateinit var concepto_EditText: EditText

    //@RequiresApi(Build.VERSION_CODES.O)
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_concepto)
        concepto_EditText = findViewById(R.id.editText_concepto)
        //var fecha_y_hora  = Instant.now().toString()
        var fecha_y_hora  = "0000-00-00 00:00:00.0000"

        val button = findViewById<Button>(R.id.boton_guardar)

        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(concepto_EditText.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                Log.d("TAG-1", concepto_EditText.text.toString())
                Log.d("TAG-1", fecha_y_hora)

                replyIntent.putExtra(EXTRA_REPLY_CONCEPTO,concepto_EditText.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_FECHA_Y_HORA,Instant.now().toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY_CONCEPTO = "com.misfinanzaspersonales.REPLY_CONCEPTO"
        const val EXTRA_REPLY_FECHA_Y_HORA = "com.misfinanzaspersonales.REPLY_FECHA_Y_HORA"
    }
}