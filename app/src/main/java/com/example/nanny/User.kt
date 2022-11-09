package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nanny.databinding.ActivityLoginBinding
import com.example.nanny.databinding.ActivityUserBinding

class User : AppCompatActivity() {
    private lateinit var binding:ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityUserBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        obtenerdatos()

        binding.btnOurNannysUser.setOnClickListener{
            startActivity(Intent(this,OurNannys::class.java))
        }

    }

    fun obtenerdatos(){
        val nombreus=binding.labelNameUser
        val bundle=intent.extras
        val dato=bundle?.getString("nombre")
        nombreus.setText(dato)
    }
}