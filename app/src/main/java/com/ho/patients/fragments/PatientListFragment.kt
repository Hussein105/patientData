package com.ho.patients.fragments

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.setMargins
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
        setHasOptionsMenu(true)
        _binding = FragmentPatientListBinding.inflate(inflater, container, false)
        mPatientViewModel = ViewModelProvider(this)[PatientViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mPatientAdapter = PatientAdapter()
        binding.rvPatients.apply {
            adapter = mPatientAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        mPatientViewModel.readAllPatient.observe(viewLifecycleOwner) {
            mPatientAdapter.setPatientData(it)
        }

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            Snackbar.make(
                requireContext(),
                it, "Please fill ALL Fields",
                Snackbar.LENGTH_SHORT
            ).show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_delete_all, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_deleteAll) {
            alertDialogBuilder()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun alertDialogBuilder() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Warning you are about to delete are the data you have collected")
        builder.setMessage("Enter the absolute key to continue")
        val password = EditText(requireContext())
        val param = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        param.setMargins(8)
        password.layoutParams = param
        password.hint = "Absolute key"
        builder.setView(password)
        builder.setIcon(R.drawable.ic_delete)
        builder.setPositiveButton("Yes") { _, _ ->
            if (password.text.toString().trim() == "Hussein1998") {
                mPatientViewModel.deleteAllData()
                Toast.makeText(
                    requireContext(),
                    "All data has been deleted!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Wrong password :)",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        builder.setNegativeButton("No") { _, _ ->
            Toast.makeText(
                requireContext(),
                "Fine, you will keep them",
                Toast.LENGTH_SHORT
            ).show()
        }
        builder.create().show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}