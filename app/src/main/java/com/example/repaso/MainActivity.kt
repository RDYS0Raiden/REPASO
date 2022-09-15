package com.example.repaso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.ButtonBarLayout
import java.util.*

class MainActivity : AppCompatActivity(),TextToSpeech.OnInitListener {
    //lateinit -> sabes que yo me resonsabilizo de iniciar esa
    // variable mas tarde
    private lateinit var txtMensaje: TextView
    private lateinit var btnProcesar: Button

    //otra manera de hacer esto/ private var mensaje:String?=null
    private var mensaje: String? = null

    //usaremos el comando de voz..
    //tts=text to speech
    private lateinit var tts: TextToSpeech


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        InicializarViews()
        // inicializar el comando de voz
        // si vien estan mandando 2 veces this , no significa que sea lo mismo
        //1) this en posicion 0 o primera posicioni, representa contacto de una activisad
        //2) this en posicion 1 o segunda posicion, va a validar que es esta clase
        //se este implementando la interfaz y por ende exista una regla de juego

        tts = TextToSpeech(this, this)
        // Funciones de orden superior es cuando lleva { }.. como filter,map
        //it .... evitaba que escriban toda la estructura de la lambda

        btnProcesar.setOnClickListener { speakMessage() }
    }

    private fun InicializarViews() {
        txtMensaje = findViewById(R.id.txtMensaje)
        btnProcesar = findViewById(R.id.btnProcesar)
        //sin tener que usar variables
        //otra forma de hacer/ findViewById<TextView>(R.id.txtMensaje)

    }

    private fun speakMessage() {

        mensaje = "Como estas bro  maquina fiera tifon master  jefe   numero uno."
        txtMensaje.text = mensaje
        tts.speak(mensaje, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    //verificar el esatado de tu comando de voz
    override fun onInit(status: Int) {
        // se esta evaluendo que el comando de voz sea exitoso
        var resultado = if (status == TextToSpeech.SUCCESS) {
            //se puede escribir 500 lineas de codigo pero la ultima tiene que ser el
            //valor que quieres que asuma esa varianle

            //por defecto el lenguaje es ingles...
            //si quieres modificar el lenguaje se hace esto: tts.language= Locale.US
            //tts.language= Locale.US
            tts.language = Locale("ES")
            "Estado funcional correcto"
        } else "Algo salio mal,pruebe despues"
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show()

    }
}