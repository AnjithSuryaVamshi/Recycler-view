package com.example.recyclerview

import android.icu.text.Transliterator.Position
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class Custom_Adapter(val listOfContacts : List<Contact>): RecyclerView.Adapter<Custom_Adapter.ContactViewHolder>() {

    class ContactViewHolder(view :View):RecyclerView.ViewHolder(view){
        val Cimg : ImageView= view.findViewById(R.id.Cimg)
        val Ntxt : TextView = view.findViewById(R.id.Ntxt)
        val Ctxt : TextView= view.findViewById(R.id.Ctxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout,parent,false)
        return ContactViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listOfContacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = listOfContacts[position]
        holder.Ntxt.text= currentContact.Ntext
        holder.Ctxt.text = currentContact.Ctext
        holder.Cimg.setImageURI(currentContact.img)
    }
}