package com.example.sqlite2020

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumno_table")
data class Alumno(@PrimaryKey var id: Int ,var name: String?, var email: String?, var code: String?)