package com.example.nanny

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nanny.databinding.ActivityOurNannysBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class OurNannys : AppCompatActivity() {

    private lateinit var binding: ActivityOurNannysBinding
    private var listN:MutableList<Nannys> = mutableListOf()
    private lateinit var recycler:RecyclerView
    private lateinit var db:FirebaseFirestore
    private lateinit var ap:NannysAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityOurNannysBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent=Intent(this,DetailsNannys::class.java)
        db=FirebaseFirestore.getInstance()

        db.collection("listNannys").addSnapshotListener(object :EventListener<QuerySnapshot>{
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if(error!=null){
                    println("Error en la firebase")
                }
                for (nan:DocumentChange in value?.documentChanges!!){
                    if (nan.type==DocumentChange.Type.ADDED){
                        listN.add(nan.document.toObject(Nannys::class.java))
                    }
                }
                ap.notifyDataSetChanged()
            }
        })

        recycler=binding.listRecyclerNanys
        recycler.layoutManager=LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        listN= mutableListOf()

        ap= NannysAdapter(this,listN!!,object:ClickListener{
            override fun onClic(vista:View, posicion:Int){
                Toast.makeText(applicationContext,listN?.get(posicion)?.names,Toast.LENGTH_LONG).show()
                val names=listN?.get(posicion)?.names
                val email=listN?.get(posicion)?.correo
                val image=listN?.get(posicion)?.image
                val phone=listN?.get(posicion)?.phone

                intent.putExtra("n",names)
                intent.putExtra("c",email)
                intent.putExtra("i",image)
                intent.putExtra("p",phone)
                startActivity(intent)

            }
        })
        recycler.adapter=ap


//        listN.add(Nannys("luisa1","luisa1@gmail.com"))
//        listN.add(Nannys("luisa2","luisa2@gmail.com"))
//        listN.add(Nannys("luisa3","luisa3@gmail.com"))
//        listN.add(Nannys("luisa4","luisa4@gmail.com"))
//        listN.add(Nannys("luisa5","luisa5@gmail.com"))
//        listN.add(Nannys("luisa6","luisa6@gmail.com"))
//        listN.add(Nannys("luisa7","luisa7@gmail.com"))
//        listN.add(Nannys("luisa8","luisa8@gmail.com"))
//        listN.add(Nannys("luisa9","luisa9@gmail.com"))
//        listN.add(Nannys("luisa10","luisa10@gmail.com"))
//        listN.add(Nannys("luisa11","luisa11@gmail.com"))
//        listN.add(Nannys("luisa12","luisa12@gmail.com"))
//        listN.add(Nannys("luisa13","luisa13@gmail.com"))
//        listN.add(Nannys("luisa14","luisa14@gmail.com"))
//        listN.add(Nannys("luisa15","luisa15@gmail.com"))

  //      stablishadapter()
    }

    private fun stablishadapter(){
        recycler=binding.listRecyclerNanys
        recycler.layoutManager=LinearLayoutManager(this)
        //recycler.adapter=NannysAdapter(this,listN)
    }
}