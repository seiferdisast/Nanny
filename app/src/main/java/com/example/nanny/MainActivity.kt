package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.nanny.databinding.ActivityMainBinding
import com.example.nanny.databinding.ActivitySplashaniBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Nanny)
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.btnLoginMain.setOnClickListener{
            startActivity(Intent(this,login::class.java))
        }
        binding.labelRegisterMain.setOnClickListener{
            startActivity(Intent(this,Register::class.java))
        }
        binding.btnAboutUsMain.setOnClickListener{
            startActivity(Intent(this,Nosotros::class.java))
        }
    }
}