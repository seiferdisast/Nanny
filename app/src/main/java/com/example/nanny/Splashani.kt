package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.nanny.databinding.ActivitySplachBinding
import com.example.nanny.databinding.ActivitySplashaniBinding

class Splashani : AppCompatActivity() {
    private lateinit var binding:ActivitySplashaniBinding
    private lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashani)

        binding= ActivitySplashaniBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        handler= Handler(Looper.myLooper()!!)
        handler.postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
        },6000)
    }
}