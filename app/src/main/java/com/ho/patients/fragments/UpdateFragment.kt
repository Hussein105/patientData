package com.ho.patients.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ho.patients.R
import com.ho.patients.database.PatientEntity
import com.ho.patients.databinding.FragmentUpdateBinding
import com.ho.patients.viewmodel.PatientViewModel

class UpdateFragment : Fragment() {
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ViewFragmentArgs>()
    private lateinit var mPatientViewModel: PatientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mPatientViewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            etUpdatePatientName.setText(args.viewCurrentPatient.name)
            etUpdatePatientDiagnosis.setText(args.viewCurrentPatient.diagnosis)
            etUpdateBloodPressure.setText(args.viewCurrentPatient.bloodPressure)
            etUpdateChiefComplain.setText(args.viewCurrentPatient.complain)
            etUpdateOxygenSaturation.setText(args.viewCurrentPatient.oxygenSaturation)
            etUpdatePastHistory.setText(args.viewCurrentPatient.pastHistory)
            etUpdatePatientAddress.setText(args.viewCurrentPatient.address)
            etUpdatePatientGender.setText(args.viewCurrentPatient.gender)
            etUpdatePatientHabits.setText(args.viewCurrentPatient.habits)
            etUpdatePresentHistory.setText(args.viewCurrentPatient.presentHistory)
            etUpdateTemp.setText(args.viewCurrentPatient.temperature)
            etUpdateTreatment.setText(args.viewCurrentPatient.treatment)


            btUpdatePatient.setOnClickListener {
                updatePatientData()
            }
        }
    }

    private fun updatePatientData() {
        val upPatientName = binding.etUpdatePatientName.text.toString().trim()
        val upPatientDiagnosis = binding.etUpdatePatientDiagnosis.text.toString().trim()
        val upPatientGender = binding.etUpdatePatientGender.text.toString().trim()
        val upPatientAddress = binding.etUpdatePatientAddress.text.toString().trim()
        val upPatientHabits = binding.etUpdatePatientHabits.text.toString().trim()
        val upPresentHistory = binding.etUpdatePresentHistory.text.toString().trim()
        val upPastHistory = binding.etUpdatePastHistory.text.toString().trim()
        val upComplain = binding.etUpdateChiefComplain.text.toString().trim()
        val upBloodPressure = binding.etUpdateBloodPressure.text.toString().trim()
        val upTemperature = binding.etUpdateTemp.text.toString().trim()
        val upSpo2 = binding.etUpdateOxygenSaturation.text.toString().trim()
        val upTreatment = binding.etUpdateTreatment.text.toString().trim()

        if (inputCheck(upPatientName, upPatientDiagnosis)) {
            val updatedPatientData =
                PatientEntity(
                    args.viewCurrentPatient.id,
                    upPatientName,
                    upPatientDiagnosis,
                    upPatientGender,
                    upPatientAddress,
                    upPatientHabits,
                    upPresentHistory,
                    upPastHistory,
                    upComplain,
                    upBloodPressure,
                    upTemperature,
                    upSpo2,
                    upTreatment
                )
            mPatientViewModel.updatePatientData(updatedPatientData)

            Toast.makeText(
                requireContext(),
                "${args.viewCurrentPatient.name} has been updated successfully",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_UpdateFragment_to_PatientListFragment)
        } else {
            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, diagnosis: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(diagnosis))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}