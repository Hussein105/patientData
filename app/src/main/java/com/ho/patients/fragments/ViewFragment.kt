package com.ho.patients.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ho.patients.R
import com.ho.patients.databinding.FragmentViewBinding
import com.ho.patients.viewmodel.PatientViewModel

class ViewFragment : Fragment() {
    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<ViewFragmentArgs>()
    private lateinit var mPatientViewModel: PatientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewBinding.inflate(inflater, container, false)
        mPatientViewModel = ViewModelProvider(this).get(PatientViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            tvViewPatientName.text = "Name: ${args.viewCurrentPatient.name}"
            if (tvViewPatientName.text == "Name: ") {
                tvViewPatientName.visibility = View.GONE
            } else {
                tvViewPatientName.visibility = View.VISIBLE
            }

            tvViewPatientAge.text = "Age: ${args.viewCurrentPatient.age} years"
            if (tvViewPatientAge.text == "Age: ") {
                tvViewPatientAge.visibility = View.GONE
            } else {
                tvViewPatientAge.visibility = View.VISIBLE
            }

            tvViewPatientDiagnosis.text = "Diagnosis: ${args.viewCurrentPatient.diagnosis}"
            if (tvViewPatientDiagnosis.text == "Diagnosis: ") {
                tvViewPatientDiagnosis.visibility = View.GONE
            } else {
                tvViewPatientDiagnosis.visibility = View.VISIBLE
            }

            tvViewBloodPressure.text = "Blood pressure: ${args.viewCurrentPatient.bloodPressure}"
            if (tvViewBloodPressure.text == "Blood pressure: ") {
                tvViewBloodPressure.visibility = View.GONE
                llViewExamination.visibility = View.GONE
            } else {
                tvViewBloodPressure.visibility = View.VISIBLE
                llViewExamination.visibility = View.VISIBLE
            }

            tvViewChiefComplain.text = "Patient Complain from: ${args.viewCurrentPatient.complain}"
            if (tvViewChiefComplain.text == "Patient Complain from: ") {
                tvViewChiefComplain.visibility = View.GONE
            } else {
                tvViewChiefComplain.visibility = View.VISIBLE
            }

            tvViewOxygenSaturation.text = "SPO2: ${args.viewCurrentPatient.oxygenSaturation}"
            if (tvViewOxygenSaturation.text == "SPO2: ") {
                tvViewOxygenSaturation.visibility = View.GONE
                tvViewOxygenSaturation.visibility = View.GONE
            } else {
                tvViewOxygenSaturation.visibility = View.VISIBLE
                llViewExamination.visibility = View.VISIBLE
            }

            tvViewPastHistory.text = "Past history: ${args.viewCurrentPatient.pastHistory}"
            if (tvViewPastHistory.text == "Past history: ") {
                tvViewPastHistory.visibility = View.GONE
            } else {
                tvViewPastHistory.visibility = View.VISIBLE
            }

            tvViewPatientAddress.text = "Patient address: ${args.viewCurrentPatient.address}"
            if (tvViewPatientAddress.text == "Patient address: ") {
                tvViewPatientAddress.visibility = View.GONE

            } else {
                tvViewPatientAddress.visibility = View.VISIBLE
            }

            tvViewPatientGender.text = "Gender: ${args.viewCurrentPatient.gender}"
            if (tvViewPatientGender.text == "Gender: ") {
                tvViewPatientGender.visibility = View.GONE
            } else {
                tvViewPatientGender.visibility = View.VISIBLE
            }

            tvViewPatientHabits.text = "Special habits: ${args.viewCurrentPatient.habits}"
            if (tvViewPatientHabits.text == "Special habits: ") {
                tvViewPatientHabits.visibility = View.GONE
            } else {
                tvViewPatientHabits.visibility = View.VISIBLE
            }

            tvViewPresentHistory.text = "Present history: ${args.viewCurrentPatient.presentHistory}"
            if (tvViewPresentHistory.text == "Present history: ") {
                tvViewPresentHistory.visibility = View.GONE
            } else {
                tvViewPresentHistory.visibility = View.VISIBLE
            }

            tvViewTemp.text = "Temperature: ${args.viewCurrentPatient.temperature}"
            if (tvViewTemp.text == "Temperature: ") {
                tvViewTemp.visibility = View.GONE
                llViewExamination.visibility = View.GONE
            } else {
                tvViewTemp.visibility = View.VISIBLE
                llViewExamination.visibility = View.VISIBLE
            }

            tvViewTreatment.text = "Treatment plan: ${args.viewCurrentPatient.treatment}"
            if (tvViewTreatment.text == "Treatment plan: ") {
                tvViewTreatment.visibility = View.GONE
                llViewTreatment.visibility = View.GONE
            } else {
                tvViewTreatment.visibility = View.VISIBLE
                llViewTreatment.visibility = View.VISIBLE
            }

            if (args.viewCurrentPatient.gender == "male" ||
                args.viewCurrentPatient.gender == "Male" &&
                args.viewCurrentPatient.gender!! <= 18.toString()
            ) {
                ivAvatar.setImageResource(R.drawable.ic_avatar_male_child)
            } else if (args.viewCurrentPatient.gender == "male" ||
                args.viewCurrentPatient.gender == "Male" &&
                args.viewCurrentPatient.gender!! >= 18.toString()
            ) {
                ivAvatar.setImageResource(R.drawable.ic_avatar_male)
            } else if (args.viewCurrentPatient.gender == "female" ||
                args.viewCurrentPatient.gender == "Female" &&
                args.viewCurrentPatient.gender!! <= 18.toString()
            ) {
                ivAvatar.setImageResource(R.drawable.ic_avatar_female_child)
            } else {
                ivAvatar.setImageResource(R.drawable.ic_avatar_female)
            }

            btViewEditPatient.setOnClickListener {
                findNavController().navigate(
                    R.id.action_viewFragment_to_UpdateFragment,
                    requireArguments()
                )
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_delete) {
            deletePatientData()
        } else {
            findNavController().navigate(R.id.action_viewFragment_to_PatientListFragment)
        }
        return true
    }

    private fun deletePatientData() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mPatientViewModel.deletePatientData(args.viewCurrentPatient)
            Toast.makeText(
                requireContext(),
                "Successfully Removed ${args.viewCurrentPatient.name} !",
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_viewFragment_to_PatientListFragment)
        }
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Your data still safe",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.setTitle("Delete ${args.viewCurrentPatient.name}")
        builder.setMessage("Confirming removing ${args.viewCurrentPatient.name}")
        builder.create().show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}