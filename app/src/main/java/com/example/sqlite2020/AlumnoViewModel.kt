package com.example.sqlite2020

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlumnoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AlumnoRepository

    val allAlumnos: LiveData<List<Alumno>>

    init {
        val alumnoDao = AlumnoRoomDatabase.getDatabase(application, viewModelScope).alumnoDao()
        repository = AlumnoRepository(alumnoDao)
        allAlumnos = repository.allAlumnos
    }

    fun getAlumno(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getAlumno(id)
    }

    fun insert(alumno: Alumno) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(alumno)
    }
}