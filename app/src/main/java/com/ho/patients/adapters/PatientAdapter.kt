package com.ho.patients.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ho.patients.R
import com.ho.patients.database.PatientEntity
import com.ho.patients.fragments.PatientListFragmentDirections
import kotlinx.android.synthetic.main.patient_list_item.view.*

class PatientAdapter : RecyclerView.Adapter<PatientAdapter.RecyclerViewHolder>() {

    private var patientsList = emptyList<PatientEntity>()


    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.patient_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentPatient = patientsList[position]
        holder.itemView.apply {
            tv_patient_name.text = currentPatient.name
            tv_patient_diagnosis.text = "Diagnosed by ${currentPatient.diagnosis}"
            tv_patient_age.text = "${currentPatient.age} years"
            if (currentPatient.gender == "male" || currentPatient.gender == "Male" && currentPatient.age!! <= 18.toString()) {
                iv_patient_image.setImageResource(R.drawable.ic_avatar_male_child)
            } else if (currentPatient.gender == "male" || currentPatient.gender == "Male" && currentPatient.age!! >= 18.toString()) {
                iv_patient_image.setImageResource(R.drawable.ic_avatar_male)
            } else if (currentPatient.gender == "female" || currentPatient.gender == "Female" && currentPatient.age!! <= 18.toString()) {
                iv_patient_image.setImageResource(R.drawable.ic_avatar_female_child)
            } else {
                iv_patient_image.setImageResource(R.drawable.ic_avatar_female)
            }

            bt_viewMore.setOnClickListener {
                val action =
                    PatientListFragmentDirections.actionPatientListFragmentToViewFragment(
                        currentPatient
                    )
                findNavController().navigate(action)
            }

            container_useless.setOnClickListener {
                val action =
                    PatientListFragmentDirections.actionPatientListFragmentToViewFragment(
                        currentPatient
                    )
                findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return patientsList.size
    }

    fun setPatientData(patientList: List<PatientEntity>) {
        this.patientsList = patientList
        notifyDataSetChanged()
    }
}
