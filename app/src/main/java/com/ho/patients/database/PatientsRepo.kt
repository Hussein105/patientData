package com.ho.patients.database

import androidx.lifecycle.LiveData

class PatientsRepo(private val patientsDao: PatientsDao) {

    val readAllPatient: LiveData<List<PatientEntity>> = patientsDao.readPatientData()

    suspend fun addPatientData(patientDataEntity: PatientEntity) {
        patientsDao.insertPatientData(patientDataEntity)
    }

    suspend fun updatePatientData(patientData: PatientEntity) {
        patientsDao.updatePatientData(patientData)
    }

    suspend fun deletePatientData(patientData: PatientEntity) {
        patientsDao.deletePatientData(patientData)
    }

    suspend fun deleteAllData() {
        patientsDao.deleteAllData()
    }
}