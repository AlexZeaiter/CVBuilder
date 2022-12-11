package com.example.cv_builder

import android.R
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.cv_builder.databinding.FragmentFirstBinding
import com.example.cv_builder.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    var educations = ArrayList<String>()
    private lateinit var adapter:  ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {


        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        educations.add("Bachelor Degree in Computer Science")
        educations.add("Masters Degree in Computer Science")
        educations.add("Extra Certification 1")
        educations.add("Extra Certification 2")
        educations.add("Other Degrees")
        binding.fabEducation.setOnClickListener { addEducation() }

        adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_dropdown_item, educations)
        binding.lview.adapter = adapter

        return binding.root
    }
    private fun addEducation() {
        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(com.example.cv_builder.R.layout.add_education,null)
        /**set view*/
        val newEducation = v.findViewById<EditText>(com.example.cv_builder.R.id.newEducation)

        val addDialog = AlertDialog.Builder(requireContext())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val nEducation = newEducation.text.toString()
            educations.add(nEducation)
            adapter.notifyDataSetChanged()
            Toast.makeText(requireContext(),"Education Information Added Successfully", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(requireContext(),"Cancel", Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
    }

}