package com.example.login_macadev

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class View_VerificationCode : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_view_verification_code)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val photeN=intent.getStringExtra("phoneN")
        val numero=findViewById<TextView>(R.id.lblTelefono)

        numero.text=photeN ?: "No recibido"

        val btnValidar=findViewById<Button>(R.id.btnFinal)
        val digit1=findViewById<EditText>(R.id.txtDigit)
        val digit2=findViewById<EditText>(R.id.txtDigit1)
        val digit3=findViewById<EditText>(R.id.txtDigit2)
        val digit4=findViewById<EditText>(R.id.txtDigit3)

        val code= listOf(digit1,digit2,digit3,digit4)

        code.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    val text = s.toString()
                    if (text.length == 1 && index < code.size - 1) {

                        // Si se ingresa un dígito, pasar al siguiente EditText
                        code[index + 1].requestFocus()

                    } else if (text.isEmpty() && index > 0) {

                        // Si se borra el texto, mover el foco al EditText anterior
                        code[index - 1].requestFocus()
                    }

                }
            })
        }
        btnValidar.setOnClickListener{
            // Verificar el código
            if (code.all { it.text.isNotEmpty() }) {
                val code = code.joinToString("") { it.text.toString() }
                validarCode(code)
            }
        }

        //cerrar el teclado al completar el digito 4
        digit4.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && digit4.text.isEmpty()) {
                digit3.requestFocus()
                true
            } else {
                false
            }
        }
    }

    //---------------------------------------------------------------------Validar el Código
    fun validarCode(code:String){

        if(code.equals("1234")){
            Toast.makeText(this, "Código verificado", Toast.LENGTH_SHORT)
                .show()
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)

            //----------------------------------------------------------Asignar el usuario
            val nombreU=intent.getStringExtra("nombre") ?: ""
            val contraU=intent.getStringExtra("contra") ?: ""
            MainActivity.agregarUsuario(nombreU,contraU)


        }else{
            Toast.makeText(this, "Código incorrecto", Toast.LENGTH_SHORT)
                .show()
        }

    }
    //---------------------------------------------------------------------Reenviar el Código
    fun reenviarCode(){

    }
}