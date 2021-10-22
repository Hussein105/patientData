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
        mPatientViewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btAddPatient.setOnClickListener {
            insertNewPatientData()
        }
    }

    private fun insertNewPatientData() {
        val patientName = binding.etPatientName.text.toString().trim()
        val patientDiagnosis = binding.etPatientDiagnosis.text.toString().trim()

        if (inputCheck(patientName, patientDiagnosis)) {
            val patientData = PatientEntity(0, patientName, patientDiagnosis)
            mPatientViewModel.addPatientData(patientData)
            Toast.makeText(requireContext(), "Saved!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_AddFragment_to_PatientListFragment)
        } else {
            Toast.makeText(requireContext(), "Failed!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(name: String, diagnosis: String): Boolean {
        return !(TextUtils.isEmpty(name) && TextUtils.isEmpty(diagnosis))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}