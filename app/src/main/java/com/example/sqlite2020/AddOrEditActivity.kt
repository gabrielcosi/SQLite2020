package com.example.sqlite2020

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_or_edit.*

class AddOrEditActivity : AppCompatActivity() {

    private lateinit var alumnoViewModel: AlumnoViewModel
    private var isEditMode = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_or_edit)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initDB()
        initOperations()
    }

    private fun initDB() {
        alumnoViewModel = ViewModelProvider(this).get(AlumnoViewModel::class.java)
        btn_delete.visibility = View.INVISIBLE
        if (intent != null && intent.getStringExtra("Mode") == "E") {
            isEditMode = true
            val alumno: Alumno = alumnoViewModel.getAlumno(intent.getIntExtra("Id",0))
            input_name.setText(alumno.name)
            input_email.setText(alumno.email)
            input_code.setText(alumno.code)
            btn_delete.visibility = View.VISIBLE
        }
    }

    private fun initOperations() {
        btn_save.setOnClickListener {
            var success: Boolean = false
            if (!isEditMode) {
                val alumno: Alumno = Alumno()
                alumno.name = input_name.text.toString()
                alumno.email = input_email.text.toString()
                alumno.code = input_code.text.toString()
                success = dbHandler?.addAlumno(alumno) as Boolean
            } else {
                val alumno: Alumno = Alumno()
                alumno.id = intent.getIntExtra("Id", 0)
                Log.d("id", alumno.id.toString());
                alumno.name = input_name.text.toString()
                alumno.email = input_email.text.toString()
                alumno.code = input_code.text.toString()
                success = dbHandler?.updateAlumno(alumno) as Boolean
            }

            if (success)
                finish()
        }

        btn_delete.setOnClickListener {
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("Click en 'SI' para eliminar el alumno")
                .setPositiveButton("SI") { dialog, _ ->
                    val success = dbHandler?.deleteAlumno(intent.getIntExtra("Id", 0)) as Boolean
                    if (success)
                        finish()
                    dialog.dismiss()
                }
                .setNegativeButton("NO") { dialog, _ ->
                    dialog.dismiss()
                }
            dialog.show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}