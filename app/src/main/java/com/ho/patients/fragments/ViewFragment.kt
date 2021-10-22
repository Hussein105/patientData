package com.ho.patients.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                findNavController().navigate(R.id.action_viewFragment_to_UpdateFragment)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}