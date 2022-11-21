package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nanny.databinding.ActivityLoginBinding
import com.example.nanny.databinding.ActivityRecoveryKeyBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecoveryKey : AppCompatActivity() {
    private lateinit var binding: ActivityRecoveryKeyBinding
    private lateinit var firebaseAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        binding = ActivityRecoveryKeyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        firebaseAuth=Firebase.auth



        binding.btnSendRecovery.setOnClickListener {
            resetPassword(binding.inputEmailRecovery.text.toString())
        }
    }
    private fun resetPassword(email:String){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(){
            task->
            if(task.isSuccessful){
                Toast.makeText(this,"Revisa la bandeja de entrada de tu correo asociado",Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun resertExamplePass(){
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