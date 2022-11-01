package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nanny.databinding.ActivityLoginBinding
import com.example.nanny.databinding.ActivityRecoveryKeyBinding

class RecoveryKey : AppCompatActivity() {
    private lateinit var binding: ActivityRecoveryKeyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityRecoveryKeyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSendRecovery.setOnClickListener {
            val email: String = binding.inputEmailRecovery.text.toString()
            if (email == "pepe") {
                startActivity(Intent(this, Login::class.java))
                Toast.makeText(
                    this,
                    "Revisa la bandeja de entrada de tu correo asociado.",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Toast.makeText(this, "Correo no registrado, intentelo de nuevo.", Toast.LENGTH_SHORT).show()
                binding.inputEmailRecovery.text = null
            }
        }
    }
}