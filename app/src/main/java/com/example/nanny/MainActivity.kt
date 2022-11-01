package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nanny.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        binding.btnLoginMain.setOnClickListener{
            startActivity(Intent(this,Login::class.java))
        }
        binding.labelRegisterMain.setOnClickListener{
            startActivity(Intent(this,Register::class.java))
        }
        binding.btnAboutUsMain.setOnClickListener{
            startActivity(Intent(this,AboutUs::class.java))
        }
    }
}