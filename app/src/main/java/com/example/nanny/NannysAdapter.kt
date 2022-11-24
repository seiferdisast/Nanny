package com.example.nanny

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NannysAdapter(private val context:Context, val listado:MutableList<Nannys>, var clickListener:ClickListener):
    RecyclerView.Adapter<NannysAdapter.ViewHolder>(){
    inner class ViewHolder(itemview:View,listener:ClickListener):RecyclerView.ViewHolder(itemview),View.OnClickListener{
        var names:TextView
        var phone:TextView
        var image:ImageView
        var listener:ClickListener?=null
        init {
            names=itemview.findViewById(R.id.labelNameCardnanny)
            phone=itemview.findViewById(R.id.labelPhoneCardnanny)
            image=itemview.findViewById(R.id.imageNanyCardnanny)
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
        holder.names.text=nanny.names
        holder.phone.text=nanny.phone
        Glide.with(holder.itemView).load(nanny.image).into((holder.image))

    }



    override fun getItemCount(): Int {
        return listado.size
    }
}