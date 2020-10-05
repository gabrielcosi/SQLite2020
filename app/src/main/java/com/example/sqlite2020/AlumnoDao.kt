package com.example.sqlite2020

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlumnoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlumnos(vararg alumno: Alumno)

    @Update
    fun updateAlumno(vararg alumno: Alumno)

    @Delete
    fun deleteAlumno(vararg alumno: Alumno)

    @Delete
    fun deleteAll(alumno: List<Alumno>)

    @Query("SELECT * FROM alumno_table")
    fun loadAllAlumnos(): List<Alumno>

    @Query("SELECT * FROM alumno_table WHERE id=id")
    fun getAlumno(id: Int): Alumno
}