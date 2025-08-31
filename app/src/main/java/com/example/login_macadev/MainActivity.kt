package com.example.login_macadev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnRegistro=findViewById<Button>(R.id.btnRegistro)

        btnRegistro.setOnClickListener {
            val intent= Intent(this, View_Register::class.java)
            startActivity(intent)
        }

    }


    companion object {
        val usuarios = mutableListOf("Carlos", "Felipe", "Pedro")
        val contras = mutableListOf("carlos22", "felipe12", "pedro15")

        fun agregarUsuario(User: String, password: String) {
            usuarios.add(User)
            contras.add(password)
        }

    }

    fun validarUsuario(){

    }
}