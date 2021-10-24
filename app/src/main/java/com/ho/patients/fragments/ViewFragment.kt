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
            tvViewPatientName.text = args.viewCurrentPatient.name
            tvViewPatientDiagnosis.text = args.viewCurrentPatient.diagnosis
            tvViewBloodPressure.text = args.viewCurrentPatient.bloodPressure
            tvViewChiefComplain.text = args.viewCurrentPatient.complain
            tvViewOxygenSaturation.text = args.viewCurrentPatient.oxygenSaturation
            tvViewPastHistory.text = args.viewCurrentPatient.pastHistory
            tvViewPatientAddress.text = args.viewCurrentPatient.address
            tvViewPatientGender.text = args.viewCurrentPatient.gender
            tvViewPatientHabits.text = args.viewCurrentPatient.habits
            tvViewPresentHistory.text = args.viewCurrentPatient.presentHistory
            tvViewTemp.text = args.viewCurrentPatient.temperature
            tvViewTreatment.text = args.viewCurrentPatient.treatment

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