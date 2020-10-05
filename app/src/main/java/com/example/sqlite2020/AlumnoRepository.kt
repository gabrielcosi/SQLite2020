package com.example.sqlite2020

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class AlumnoRepository(private val alumnoDao: AlumnoDao) {
    val allAlumnos: LiveData<List<Alumno>> = alumnoDao.loadAllAlumnos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(alumno: Alumno) {
        alumnoDao.insertAlumno(alumno)
    }

    suspend fun getAlumno(id: Int) {

    }
}