package com.example.sqlite2020

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var alumnoRecyclerAdapter: AlumnoRecyclerAdapter? = null;
    private var fab: FloatingActionButton? = null
    private var recyclerView: RecyclerView? = null
    private var listAlumnos: List<Alumno> = ArrayList<Alumno>()
    private var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initOperations()
        //initDB()
    }

    private fun initDB() {
        val db = Room.databaseBuilder(
            applicationContext,
            AlumnoRoomDatabase::class.java, "database-name").build()
        listAlumnos = db.alumnoDao().loadAllAlumnos()
        alumnoRecyclerAdapter = AlumnoRecyclerAdapter(alumnoList = listAlumnos, context = applicationContext)
        (recyclerView as RecyclerView).adapter = alumnoRecyclerAdapter
    }

    private fun initViews() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recycler_view)
        alumnoRecyclerAdapter = AlumnoRecyclerAdapter(alumnoList = listAlumnos, context = applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        (recyclerView as RecyclerView).layoutManager = linearLayoutManager
    }

    private fun initOperations() {
        fab?.setOnClickListener {
            val i = Intent(applicationContext, AddOrEditActivity::class.java)
            i.putExtra("Mode", "A")
            startActivity(i)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_delete) {
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click en 'SI' borrara todos los alumnos")
                .setPositiveButton("SI") { dialog, _ ->
                    dbHandler!!.deleteAllAlumnos()
                    initDB()
                    dialog.dismiss()
                }
                .setNegativeButton("NO") { dialog, _ ->
                    dialog.dismiss()
                }
            dialog.show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        initDB()
    }
}