package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nanny.databinding.ActivityLoginBinding
import com.example.nanny.databinding.ActivityUserBinding
import com.google.firebase.firestore.FirebaseFirestore

class User : AppCompatActivity() {
    private lateinit var binding:ActivityUserBinding
    private val bd=FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityUserBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        obtenerdatos()
        val bundle=intent.extras
        val data=bundle?.getString("ide")
        consulUserData(data?:"")



        binding.btnOurNannysUser.setOnClickListener{
            startActivity(Intent(this,OurNannys::class.java))
        }

        binding.btnExitUser.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    fun obtenerdatos(){
        val nombreus=binding.labelNameUser
        val bundle=intent.extras
        val dato=bundle?.getString("nombre")
        nombreus.setText(dato)
    }

    private fun consulUserData(id:String){
        bd.collection("ojo aqui nombre colleccion en FB").document(id).get().addOnSuccessListener {
            binding.labelNameUser.setText(it.get("nombre")as String?)
        }

    }

}