package com.example.nanny

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NannysAdapter(private val context: OurNannys, val listado:MutableList<Nannys>):
    RecyclerView.Adapter<NannysAdapter.ViewHolder>(){
    inner class ViewHolder(itemview:View):RecyclerView.ViewHolder(itemview){
        lateinit var datos:TextView
        lateinit var datos2:TextView
        init {
            datos=itemview.findViewById(R.id.labelNameCardnanny)
            datos2=itemview.findViewById(R.id.labelPhoneCardnanny)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemview=LayoutInflater.from(parent.context).inflate(R.layout.cardnannys,parent,false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nanny=listado[position]
        holder.datos.text=nanny.nombre
        holder.datos2.text=nanny.correo

    }



    override fun getItemCount(): Int {
        return listado.size
    }
}