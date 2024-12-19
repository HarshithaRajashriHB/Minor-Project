package com.example.scholator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// ScholarshipAdapter class to bind the data to RecyclerView
class ScholarshipAdapter(private val scholarshipList: List<Scholarships>) : RecyclerView.Adapter<ScholarshipAdapter.ScholarshipViewHolder>() {

    // Called when RecyclerView needs a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScholarshipViewHolder {
        // Inflate the individual item layout (scholarship_item.xml)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.scholarship_item, parent, false)
        return ScholarshipViewHolder(itemView)
    }

    // Called to bind the data to the view (each scholarship item)
    override fun onBindViewHolder(holder: ScholarshipViewHolder, position: Int) {
        val currentScholarship = scholarshipList[position]
        holder.scholarshipName.text = currentScholarship.name
        holder.scholarshipDescription.text = currentScholarship.description
    }

    // Returns the number of items in the dataset
    override fun getItemCount() = scholarshipList.size

    // ViewHolder class to hold references to views in the item layout
    class ScholarshipViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val scholarshipName: TextView = itemView.findViewById(R.id.scholarship_name)
        val scholarshipDescription: TextView = itemView.findViewById(R.id.scholarship_description)
    }
}
