package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nanny.databinding.ActivityAboutUsBinding
import com.example.nanny.databinding.ActivityLoginBinding

class AboutUs : AppCompatActivity() {

    private lateinit var binding: ActivityAboutUsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAboutUsBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.labelLocalizacionOfficeAboutUs.setOnClickListener{
            startActivity(Intent(this,GoogleMaps::class.java))
        }
    }
}