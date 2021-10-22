package com.ho.patients.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ho.patients.database.PatientEntity
import com.ho.patients.database.PatientsDatabase
import com.ho.patients.database.PatientsRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PatientViewModel(application: Application) : AndroidViewModel(application) {

    val readAllPatient: LiveData<List<PatientEntity>>
    private val patientsRepo: PatientsRepo

    init {
        val patientDao = PatientsDatabase.getDatabase(application).patientDao()
        patientsRepo = PatientsRepo(patientDao)
        readAllPatient = patientsRepo.readAllPatient
    }

    fun addPatientData(patient: PatientEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            patientsRepo.addPatientData(patient)
        }
    }

    fun updatePatientData(patient: PatientEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            patientsRepo.updatePatientData(patient)
        }
    }

    fun deletePatientData(patient: PatientEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            patientsRepo.deletePatientData(patient)
        }
    }
}