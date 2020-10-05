package com.example.sqlite2020

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Alumno::class], version = 1)
abstract class AlumnoRoomDatabase : RoomDatabase() {
    abstract fun alumnoDao() : AlumnoDao
}
