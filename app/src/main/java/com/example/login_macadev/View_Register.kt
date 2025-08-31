package com.example.login_macadev

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class View_Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_register)

        //variables de entrada
        val nameUser=findViewById<EditText>(R.id.txtNameUser)
        val password=findViewById<EditText>(R.id.txtPassUser)
        val confirPass=findViewById<EditText>(R.id.txtConfirPassword)
        val phoneNumber=findViewById<EditText>(R.id.txtPhoteUser)

        val btnRegisUser=findViewById<Button>(R.id.btnCreateUser)


        btnRegisUser.setOnClickListener {

            var nombre=nameUser.text.toString()
            var contra=password.text.toString()
            var confirContra=confirPass.text.toString()
            var telefono=phoneNumber.text.toString()

            when {
                nombre.isEmpty() || contra.isEmpty() ||  confirContra.isEmpty() ||  telefono.isEmpty()-> {
                    // Condición si el campo está vacío
                    Toast.makeText(this, "Campos vacios, completar todos los campos", Toast.LENGTH_SHORT)
                        .show()
                }

                nombre.length < 3 -> {
                    // Condición si el texto es muy corto
                    nameUser.error = "El nombre debe de tener minímo 3 caracteres"
                }

                contra.matches(Regex(".*\\d.*")) ->{
                    password.error = "La contraseña debe contener al menos un dígito (0-9)"
                }

                contra.length< 6 -> {
                    password.error="La contraseña debe tener minímo de 6 caracteres"
                }

                confirContra!=contra ->{
                    confirPass.error="Las contraseñas no concuerdan"
                }
                else -> {
                    // Condición por defecto
                    Toast.makeText(this, "Cuenta creada con exito", Toast.LENGTH_SHORT)
                        .show()

                    val intent= Intent(this, View_VerificationCode::class.java)
                    intent.putExtra("phoneN",telefono)
                    intent.putExtra("nombre",nombre)
                    intent.putExtra("contra",contra)
                    startActivity(intent)

                }
            }

        }
    }
    fun validarContra(){

    }
}