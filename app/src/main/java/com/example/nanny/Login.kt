package com.example.nanny

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.nanny.data.UserData
import com.example.nanny.databinding.ActivityLoginBinding
import com.example.nanny.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private val bd = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAuth = Firebase.auth
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        binding.btnEnterLogin.setOnClickListener {
            val email = binding.inputEmailLogin.text.toString()
            val password = binding.inputPasswordLogin.text.toString()
            if(email!!.isEmpty() && password!!.isEmpty()){
                Toast.makeText(this, "Ingrese sus datos", Toast.LENGTH_SHORT).show()
            }else{
                if (email!!.isEmpty()) {
                    binding.inputEmailLogin.setText("")
                    binding.inputEmailLogin.setHint("Ingrese su correo")
                    Toast.makeText(this, "Ingrese un correo", Toast.LENGTH_SHORT).show()
                } else if (password!!.isEmpty()) {
                    binding.inputPasswordLogin.setText("")
                    binding.inputPasswordLogin.setHint("Ingrese su contraseña")
                    Toast.makeText(this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show()

                } else {
                    authlogin(email, password)
                }
            }
            }

        binding.labelRecoveryPassLogin.setOnClickListener {
            startActivity(Intent(this, RecoveryKey::class.java))
        }
    }


    private fun authlogin(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val id = firebaseAuth.uid
                    Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
                    val intent = Intent(this, User::class.java)
                    intent.putExtra("ide", id)
                    startActivity(intent)
                    binding.inputEmailLogin.setText("")
                    binding.inputPasswordLogin.setText("")
                } else {
                    binding.inputEmailLogin.setText("")
                    binding.inputEmailLogin.setHint("Ingrese el correo")
                    binding.inputPasswordLogin.setText("")
                    binding.inputPasswordLogin.setHint("Ingrese el contraseña")
                    Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_LONG).show()
                }
            }
    }

}