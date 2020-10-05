package com.example.sqlite2020

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AlumnoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAlumno(vararg alumno: Alumno)

    @Update
    fun updateAlumno(vararg alumno: Alumno)

    @Query("DELETE FROM alumno_table WHERE id = :id")
    fun deleteAlumno(id: Int)

    @Query("DELETE FROM alumno_table")
    fun deleteAll()

    @Query("SELECT * FROM alumno_table")
    fun loadAllAlumnos(): LiveData<List<Alumno>>

    @Query("SELECT * FROM alumno_table WHERE id = :id")
    fun getAlumno(id: Int): Alumno
}