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
        val name=bundle?.getString("names")
        val address=bundle?.getString("adreess")
        val phone=bundle?.getString("phone")
        val email=bundle?.getString("email")
        val roll=bundle?.getString("roll")
        val image=bundle?.getString("image")
        binding.labelNamesDetailsNannis.setText(name)
        binding.labelAddressDetailsNannis.setText(address)
        binding.labelPhoneDetailsNannis.setText(phone)
        binding.labelEmailDetailsNannis.setText(email)
        binding.labelRollDetailsNannis.setText(roll)
        Glide.with(this).load(image).into(binding.imageViewDetailsNannis)

    }
}