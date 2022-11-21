package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nanny.databinding.ActivityUserBinding
import com.google.firebase.firestore.FirebaseFirestore

class User : AppCompatActivity() {
    private lateinit var binding:ActivityUserBinding
    private val bd=FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        val bundle=intent.extras
        val data=bundle?.getString("ide")
        consulUserData(data?:"")

        super.onCreate(savedInstanceState)

        binding= ActivityUserBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        binding.btnOurNannysUser.setOnClickListener{
            startActivity(Intent(this,OurNannys::class.java))
        }

        binding.btnExitUser.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }

    }

    private fun consulUserData(id:String){
        bd.collection("appUser").document(id).get().addOnSuccessListener {
            binding.labelNameUser.setText(it.get("names")as String?)
            binding.labelPhoneUser.setText(it.get("phone")as String?)
        }

    }

}