package com.ho.patients.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ho.patients.R
import com.ho.patients.adapters.PatientAdapter
import com.ho.patients.databinding.FragmentPatientListBinding
import com.ho.patients.viewmodel.PatientViewModel

class PatientListFragment : Fragment() {

    private var _binding: FragmentPatientListBinding? = null

    private lateinit var mPatientViewModel: PatientViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPatientListBinding.inflate(inflater, container, false)
        mPatientViewModel = ViewModelProvider(this).get(PatientViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mPatientAdapter = PatientAdapter()
        binding.rvPatients.apply {
            adapter = mPatientAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        mPatientViewModel.readAllPatient.observe(viewLifecycleOwner, {
            mPatientAdapter.setPatientData(it)
        })

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Snackbar.make(
                requireContext(),
                it, "Please fill ALL Fields",
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}