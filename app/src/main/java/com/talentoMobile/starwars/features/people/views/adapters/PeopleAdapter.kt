package com.davidups.starwars.features.people.views.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidups.starwars.R
import com.davidups.starwars.core.extensions.inflate
import com.davidups.starwars.features.people.views.PersonView
import kotlinx.android.synthetic.main.item_people_row.view.*
import kotlin.properties.Delegates

class PeopleAdapter: RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    internal var collection: List<PersonView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    internal var clickListener: (PersonView) -> Unit = { }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_people_row))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position], clickListener)
    }

    override fun getItemCount() = collection.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(personView: PersonView, clickListener: (PersonView) -> Unit) {
            itemView.tvPersonName.text = personView.name
            itemView.setOnClickListener { clickListener(personView) }
        }
    }
}