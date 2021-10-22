package com.ho.patients.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "patients")
data class PatientEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "diagnosis") var diagnosis: String,
    @ColumnInfo(name = "gender") var gender: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "habits") var habits: String,
    @ColumnInfo(name = "present_history") var presentHistory: String,
    @ColumnInfo(name = "past_history") var pastHistory: String,
    @ColumnInfo(name = "complain") var complain: String,
    @ColumnInfo(name = "blood_pressure") var bp: String,
    @ColumnInfo(name = "temperature") var temperature: String,
    @ColumnInfo(name = "oxygen_saturation") var spo2: String,
    @ColumnInfo(name = "treatment") var treatment: String
) : Parcelable
