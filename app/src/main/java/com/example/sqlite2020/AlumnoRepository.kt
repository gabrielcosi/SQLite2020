package com.example.sqlite2020

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class AlumnoRepository(private val alumnoDao: AlumnoDao) {
    val allAlumnos: LiveData<List<Alumno>> = alumnoDao.loadAllAlumnos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addAlumno(alumno: Alumno) {
        alumnoDao.addAlumno(alumno)
    }

    fun getAlumno(id: Int) : Alumno{
        return alumnoDao.getAlumno(id)
    }

    fun updateAlumno(alumno: Alumno) {
        alumnoDao.updateAlumno(alumno)
    }

    fun deleteAlumno(id: Int) {
        alumnoDao.deleteAlumno(id)
    }
}