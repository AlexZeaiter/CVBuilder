package com.example.cv_builder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cv_builder.databinding.FragmentWorkBinding
import com.google.android.material.snackbar.Snackbar

class Work : Fragment() {
    private var _binding: FragmentWorkBinding? = null
    private val binding get() = _binding!!
    private var jobs = ArrayList<Job>()
    private lateinit var adapter:MyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkBinding.inflate(inflater, container, false)
        binding.fabWork.setOnClickListener { addJob() }
        jobs.add(Job("List Of Work Experiances",""))
        jobs.add(Job("Job Title: Junior Software Developer at a Company","Job Description: Learning how to code with C#"))
        jobs.add(Job("Job Title: Software Engineer at a Company","Job Description: Developing in .Net"))
        jobs.add(Job("Job Title: Senior Software Engineer","Job Description: Developing and Teaching C#"))

        // Set the Layout Manager
        binding.recyclerView1.layoutManager = LinearLayoutManager(activity)
        // Create an object for the MyAdapter
        adapter = MyAdapter(jobs)
        // Set adapter to your RecyclerView
        binding.recyclerView1.adapter = adapter
        return binding.root
    }
    private fun addJob() {
        val inflter = LayoutInflater.from(requireContext())
        val v = inflter.inflate(R.layout.add_item,null)
        /**set view*/
        val jobTitle = v.findViewById<EditText>(R.id.jobTitle)
        val jobDesc = v.findViewById<EditText>(R.id.jobDesc)

        val addDialog = AlertDialog.Builder(requireContext())

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val jTitle = jobTitle.text.toString()
            val jDesc = jobDesc.text.toString()
            jobs.add(Job("Job Title: $jTitle","Job Description : $jDesc"))
            adapter.notifyDataSetChanged()
            Toast.makeText(requireContext(),"Job Information Added Successfully", Toast.LENGTH_SHORT).show()
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