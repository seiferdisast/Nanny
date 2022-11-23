package com.example.nanny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.nanny.databinding.ActivityDetailsNannysBinding
import com.example.nanny.databinding.ActivityUserBinding

class DetailsNannys : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsNannysBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityDetailsNannysBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle=intent.extras
        val name=bundle?.getString("n")
        val address=bundle?.getString("a")
        val phone=bundle?.getString("p")
        val email=bundle?.getString("c")
        val roll=bundle?.getString("r")
        val image=bundle?.getString("i")
        binding.labelNamesDetailsNannis.setText(name)
        binding.labelAddressDetailsNannis.setText(address)
        binding.labelPhoneDetailsNannis.setText(phone)
        binding.labelEmailDetailsNannis.setText(email)
        binding.labelRollDetailsNannis.setText(roll)
        Glide.with(this).load(image).into(binding.imageViewDetailsNannis)

    }
}