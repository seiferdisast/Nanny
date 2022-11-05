package com.example.nanny

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nanny.databinding.ActivityOurNannysBinding

class OurNannys : AppCompatActivity() {

    private lateinit var binding: ActivityOurNannysBinding
    private var listN:MutableList<Nannys> = mutableListOf()
    private lateinit var recycler:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOurNannysBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listN.add(Nannys("luisa1","luisa1@gmail.com"))
        listN.add(Nannys("luisa2","luisa2@gmail.com"))
        listN.add(Nannys("luisa3","luisa3@gmail.com"))
        listN.add(Nannys("luisa4","luisa4@gmail.com"))
        listN.add(Nannys("luisa5","luisa5@gmail.com"))
        listN.add(Nannys("luisa6","luisa6@gmail.com"))
        listN.add(Nannys("luisa7","luisa7@gmail.com"))
        listN.add(Nannys("luisa8","luisa8@gmail.com"))
        listN.add(Nannys("luisa9","luisa9@gmail.com"))
        listN.add(Nannys("luisa10","luisa10@gmail.com"))
        listN.add(Nannys("luisa11","luisa11@gmail.com"))
        listN.add(Nannys("luisa12","luisa12@gmail.com"))
        listN.add(Nannys("luisa13","luisa13@gmail.com"))
        listN.add(Nannys("luisa14","luisa14@gmail.com"))
        listN.add(Nannys("luisa15","luisa15@gmail.com"))

        stablishadapter()
    }

    private fun stablishadapter(){
        recycler=binding.listRecyclerNanys
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.adapter=NannysAdapter(this,listN)
    }
}