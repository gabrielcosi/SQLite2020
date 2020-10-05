package com.example.sqlite2020

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Alumno::class], version = 1)
abstract class AlumnoRoomDatabase : RoomDatabase() {

    abstract fun alumnoDao() : AlumnoDao

    companion object {
        @Volatile
        private var INSTANCE: AlumnoRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AlumnoRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlumnoRoomDatabase::class.java,
                    "alumno_database"
                )
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
