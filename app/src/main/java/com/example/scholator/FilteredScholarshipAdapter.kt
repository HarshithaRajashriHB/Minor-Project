package com.example.scholator

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class FilteredScholarshipAdapter(
    private val context: Context,
    private val scholarships: List<Scholarships>?
) : BaseAdapter() {

    override fun getCount(): Int {
        return scholarships?.size ?: 0  // Return 0 if scholarships is null or empty
    }

    override fun getItem(position: Int): Any? {
        return scholarships?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_scholarship, parent, false)

        val nameTextView: TextView = view.findViewById(R.id.scholarship_name)
        val offeredbyTextView: TextView = view.findViewById(R.id.scholarship_offeredby)
        val linkTextView: TextView = view.findViewById(R.id.scholarship_link)

        val scholarship = scholarships?.get(position)

        if (scholarship != null) {
            // Set scholarship name, description, and link
            nameTextView.text = scholarship.name
            offeredbyTextView.text = scholarship.offered_by  // Assuming 'description' is a field in the Scholarship object
            val link = scholarship.link // Assuming 'link' is a field in the Scholarship object

            // Set the link
            linkTextView.text = link

            // Make the link clickable
            linkTextView.setOnClickListener {
                // Use Intent to open the URL in a browser
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                context.startActivity(intent)
            }
        }

        return view
    }


}
