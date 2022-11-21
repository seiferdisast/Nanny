package com.example.nanny

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView.OnChildClickListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NannysAdapter(private val context:Context, val listado:MutableList<Nannys>, var clickListener:ClickListener):
    RecyclerView.Adapter<NannysAdapter.ViewHolder>(){
    inner class ViewHolder(itemview:View,listener:ClickListener):RecyclerView.ViewHolder(itemview),View.OnClickListener{
        var datos:TextView
        var datos2:TextView
        var listener:ClickListener?=null
        init {
            datos=itemview.findViewById(R.id.labelNameCardnanny)
            datos2=itemview.findViewById(R.id.labelPhoneCardnanny)
            this.listener=listener
            itemview.setOnClickListener(this)

        }

        override fun onClick(p0: View?) {
            this.listener?.onClic(p0!!,adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.cardnannys,parent,false)
        return ViewHolder(view,clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nanny=listado[position]
        holder.datos.text=nanny.Nombre
        holder.datos2.text=nanny.correo
        //Glide.with(holder.itemView).load()

    }



    override fun getItemCount(): Int {
        return listado.size
    }
}