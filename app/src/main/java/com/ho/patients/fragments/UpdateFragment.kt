package com.ho.patients.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
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
        mPatientViewModel = ViewModelProvider(this)[PatientViewModel::class.java]

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startDataHandler()
        binding.btUpdatePatient.setOnClickListener {
            updatePatientData()
        }
    }

    private fun startDataHandler() {
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
            etUpdatePatientAge.setText(args.viewCurrentPatient.age)
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
        val upAge = binding.etUpdatePatientAge.text.toString().trim()

        if (upPatientName == "" || upPatientDiagnosis == "" || upPatientGender == "" || upAge == "") {
            Toast.makeText(
                requireContext(),
                "Patient name, age, correct gender or diagnosis cannot be empty, Please check again!",
                Toast.LENGTH_SHORT
            ).show()
        } else if (
            upPatientName == args.viewCurrentPatient.name
            && upPatientDiagnosis == args.viewCurrentPatient.diagnosis
            && upPatientGender == args.viewCurrentPatient.gender
            && upPatientAddress == args.viewCurrentPatient.address
            && upPatientHabits == args.viewCurrentPatient.habits
            && upPresentHistory == args.viewCurrentPatient.presentHistory
            && upPastHistory == args.viewCurrentPatient.pastHistory
            && upComplain == args.viewCurrentPatient.complain
            && upBloodPressure == args.viewCurrentPatient.bloodPressure
            && upTemperature == args.viewCurrentPatient.temperature
            && upSpo2 == args.viewCurrentPatient.oxygenSaturation
            && upTreatment == args.viewCurrentPatient.treatment
            && upAge == args.viewCurrentPatient.age
        ) {
            Toast.makeText(
                requireContext(),
                "No changes to be saved, Please check again!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
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
                    upTreatment,
                    upAge
                )
            mPatientViewModel.updatePatientData(updatedPatientData)
            Toast.makeText(
                requireContext(),
                "${args.viewCurrentPatient.name} has been updated successfully",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_UpdateFragment_to_PatientListFragment)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(
            requireContext(),
            "All data modifications have been discarded",
            Toast.LENGTH_SHORT
        ).show()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}