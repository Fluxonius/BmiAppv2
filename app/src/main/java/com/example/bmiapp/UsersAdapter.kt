package com.example.bmiapp

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater

import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class UsersAdapter(val users:ArrayList<String>, val butts:MutableList<String>,val names:MutableList<String>,val colors:MutableList<Int>,val dates:MutableList<String>):RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
val number:TextView=itemView.findViewById(R.id.number)
        val butt:TextView=itemView.findViewById(R.id.wartosc)
        val name:TextView=itemView.findViewById(R.id.imie)
        val date:TextView=itemView.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val view: View=LayoutInflater.from(parent.context).inflate(R.layout.user_row,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount()= butts.size


    override fun onBindViewHolder(holder:ViewHolder, pos: Int) {
        holder.date.text=dates[pos]
        holder.number.text=users[pos]
        holder.butt.text=butts[pos]
        holder.butt.setTextColor(colors[pos])
        holder.name.text=(names[pos]+":")
    }

}