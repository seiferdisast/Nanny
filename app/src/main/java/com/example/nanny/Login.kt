package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nanny.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.btnEnterLogin.setOnClickListener{
            val usuario:String=binding.inputEmailLogin.text.toString()
            val clave:String=binding.inputPasswordLogin.text.toString()
            if (usuario=="pepe" && clave=="123"){
                startActivity(Intent(this,User::class.java))
            }
            else{
                Toast.makeText(this,"Datos incorrectos",Toast.LENGTH_SHORT).show()
            }
        }
        binding.labelRecoveryPass.setOnClickListener{
            startActivity(Intent(this,RecoveryKey::class.java))
        }
    }
}