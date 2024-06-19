package com.example.hope.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.Spannable
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hope.R
import com.example.hope.model.Event


class EventAdapter(private val eventList: ArrayList<Event>, private val context: Context) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private var filteredList: List<Event> = eventList
    private var searchText: String = ""

    class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventImage: ImageView = view.findViewById(R.id.eventImage)
        val eventDate: TextView = view.findViewById(R.id.eventDate)
        val eventTitle: TextView = view.findViewById(R.id.eventTitle)
        val eventLocation: TextView = view.findViewById(R.id.eventLocation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_item, parent, false)
        return EventViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = filteredList[position]
        holder.eventImage.setImageResource(event.imageResId)
        holder.eventDate.text = event.date
        holder.eventTitle.text = getHighlightedText(event.title, searchText)

        // Create a SpannableString with the display text
        val displayText = "Visit Website"
        val spannableString = SpannableString(displayText)

        // Create a ClickableSpan
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.location))
                context.startActivity(intent)
            }
        }

        // Apply the ClickableSpan to the SpannableString
        spannableString.setSpan(clickableSpan, 0, displayText.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        // Set the SpannableString on the TextView
        holder.eventLocation.text = spannableString
        holder.eventLocation.movementMethod = LinkMovementMethod.getInstance()
    }

    fun filterList(text: String) {
        searchText = text
        filteredList = if (text.isEmpty()) {
            eventList
        } else {
            eventList.filter { it.title.contains(text, ignoreCase = true) }
        }
        notifyDataSetChanged()
    }

    private fun getHighlightedText(originalText: String, searchText: String): SpannableString {
        val spannableString = SpannableString(originalText)
        if (searchText.isNotEmpty()) {
            val startIndex = originalText.indexOf(searchText, ignoreCase = true)
            if (startIndex != -1) {
                val endIndex = startIndex + searchText.length
                val backgroundSpan = BackgroundColorSpan(Color.YELLOW)
                spannableString.setSpan(backgroundSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
        return spannableString
    }
}
