package com.example.sqlite2020

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DatabaseHandler.DB_NAME, null, DatabaseHandler.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $NAME TEXT,$EMAIL TEXT,$CODE TEXT);"
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun addAlumno(alumno: Alumno): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, alumno.name)
        values.put(EMAIL, alumno.email)
        values.put(CODE, alumno.code)
        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        return (Integer.parseInt("$success") != -1)
    }

    fun getAlumno(_id: Int): Alumno {
        val alumno = Alumno()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(selectQuery, null)

        cursor?.moveToFirst()
        alumno.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        alumno.name = cursor.getString(cursor.getColumnIndex(NAME))
        alumno.email = cursor.getString(cursor.getColumnIndex(EMAIL))
        alumno.code = cursor.getString(cursor.getColumnIndex(CODE))
        cursor.close()
        return alumno
    }

    fun alumnos(): List<Alumno> {
        val alumnosList = ArrayList<Alumno>()
        val db = writableDatabase
        val selectQuery = "SELECT  * FROM $TABLE_NAME"
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val alumno = Alumno()
                    alumno.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    alumno.name = cursor.getString(cursor.getColumnIndex(NAME))
                    alumno.email = cursor.getString(cursor.getColumnIndex(EMAIL))
                    alumno.code = cursor.getString(cursor.getColumnIndex(CODE))
                    alumnosList.add(alumno)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return alumnosList
    }

    fun updateAlumno(alumno: Alumno): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NAME, alumno.name)
        values.put(EMAIL, alumno.email)
        values.put(CODE, alumno.code)
        val success = db.update(TABLE_NAME, values, "$ID=?", arrayOf(alumno.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun deleteAlumno(_id: Int): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME, "$ID=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun deleteAllAlumnos(): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME, null, null).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    companion object {
        private const val DB_VERSION = 1
        private const val DB_NAME = "Prueba"
        private const val TABLE_NAME = "Alumnos"
        private const val ID = "Id"
        private const val NAME = "Name"
        private const val EMAIL = "Email"
        private const val CODE = "Code"
    }
}