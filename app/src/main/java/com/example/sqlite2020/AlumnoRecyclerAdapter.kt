package com.example.sqlite2020

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView


import java.util.ArrayList

class AlumnoRecyclerAdapter(alumnoList: List<Alumno>, private var context: Context) : RecyclerView.Adapter<AlumnoRecyclerAdapter.AlumnoViewHolder>() {

    private var alumnoList: List<Alumno> = ArrayList()
    init {
        this.alumnoList = alumnoList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_alumnos, parent, false)
        return AlumnoViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val alumnos = alumnoList[position]
        holder.name.text = alumnos.name
        holder.email.text = alumnos.email
        holder.code.text = alumnos.code

        holder.itemView.setOnClickListener {
            val i = Intent(context, AddOrEditActivity::class.java)
            i.putExtra("Mode", "E")
            i.putExtra("Id", alumnos.id)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return alumnoList.size
    }

    inner class AlumnoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName) as TextView
        var code: TextView = view.findViewById(R.id.tvCode) as TextView
        var email: TextView = view.findViewById(R.id.tvEmail) as TextView
        var list_item: LinearLayout = view.findViewById(R.id.list_item) as LinearLayout
    }

}