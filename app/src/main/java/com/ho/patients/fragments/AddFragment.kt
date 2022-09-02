package com.ho.patients.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.ho.patients.R
import com.ho.patients.database.PatientEntity
import com.ho.patients.databinding.FragmentAddBinding
import com.ho.patients.viewmodel.PatientViewModel

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mPatientViewModel: PatientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mPatientViewModel = ViewModelProvider(this)[PatientViewModel::class.java]
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btAddPatient.setOnClickListener {
                insertNewPatientData(it)
            }
        }
    }

    private fun insertNewPatientData(view: View) {
        val patientName = binding.etPatientName.text.toString().trim()
        val patientDiagnosis = binding.etPatientDiagnosis.text.toString().trim()
        val patientGender = binding.etPatientGender.text.toString().trim()
        val patientAddress = binding.etPatientAddress.text.toString().trim()
        val patientHabits = binding.etPatientHabits.text.toString().trim()
        val presentHistory = binding.etPresentHistory.text.toString().trim()
        val pastHistory = binding.etPastHistory.text.toString().trim()
        val complain = binding.etChiefComplain.text.toString().trim()
        val bloodPressure = binding.etBloodPressure.text.toString().trim()
        val temperature = binding.etTemp.text.toString().trim()
        val oxygenSaturation = binding.etOxygenSaturation.text.toString().trim()
        val treatment = binding.etTreatment.text.toString().trim()
        val age = binding.etPatientAge.text.toString().trim()

        if (patientName != "" && patientDiagnosis != "" && patientGender != "" && age != "") {
            if (patientGender == "male" || patientGender == "Male" || patientGender == "female" || patientGender == "Female") {
                val patientData = PatientEntity(
                    0,
                    patientName,
                    patientDiagnosis,
                    patientGender,
                    patientAddress,
                    patientHabits,
                    presentHistory,
                    pastHistory,
                    complain,
                    bloodPressure,
                    temperature,
                    oxygenSaturation,
                    treatment,
                    age
                )
                mPatientViewModel.addPatientData(patientData)
                Toast.makeText(requireContext(), getString(R.string.saved), Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_AddFragment_to_PatientListFragment)
            }
        } else {
            launchSandbar(view, getString(R.string.update_rejected))
        }
    }

    private fun launchSandbar(view: View, text: String) {
        Snackbar.make(
            view,
            text,
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}