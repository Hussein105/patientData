package com.ho.patients.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

    private val args by navArgs<UpdateFragmentArgs>()
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
            etUpdatePatientName.setText(args.currentPatient.name)
            etUpdatePatientDiagnosis.setText(args.currentPatient.diagnosis)

            btUpdatePatient.setOnClickListener {
                updatePatientData()
            }

            btDelete.setOnClickListener {
                deletePatientData()
            }
        }
    }

    private fun deletePatientData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mPatientViewModel.deletePatientData(args.currentPatient)
            Toast.makeText(
                requireContext(),
                "Successfully Removed ${args.currentPatient.name} !",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_FirstFragment)
        }
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Your data still safe",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setTitle("Delete ${args.currentPatient.name}")
        builder.setMessage("Confirming removing ${args.currentPatient.name}")
        builder.create().show()
    }

    private fun updatePatientData() {
        val patientName = binding.etUpdatePatientName.text.toString()
        val patientDiagnosis = binding.etUpdatePatientDiagnosis.text.toString()

        if (inputCheck(patientName, patientDiagnosis)) {
            val updatedPatientData =
                PatientEntity(args.currentPatient.id, patientName, patientDiagnosis)
            mPatientViewModel.updatePatientData(updatedPatientData)

            Toast.makeText(requireContext(), "Updated successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_FirstFragment)
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