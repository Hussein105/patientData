package com.ho.patients.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PatientsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPatientData(patient: PatientEntity)

    @Query("SELECT * FROM patients ORDER BY id DESC")
    fun readPatientData(): LiveData<List<PatientEntity>>

    @Update
    suspend fun updatePatientData(patientData: PatientEntity)

    @Delete
    suspend fun deletePatientData(patient: PatientEntity)
}